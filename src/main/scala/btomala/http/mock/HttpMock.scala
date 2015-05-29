package btomala.http.mock

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.ActorFlowMaterializer
import akka.stream.scaladsl.Sink
import com.typesafe.config.{Config, ConfigFactory}

class HttpMock(config: Config = ConfigFactory.load)(implicit system: ActorSystem, materializer: ActorFlowMaterializer) {

  val host = config.getString("mock.http.interface")
  val port = config.getInt("mock.http.port")
  val path = s"http://$host:$port/"

  private val recorded = scala.collection.mutable.Map[HttpRequest, HttpResponse]()

  private val requests: HttpRequest => HttpResponse = { request ⇒
  val response = recorded.get(request).getOrElse(notRecordedResponse(request.protocol))
    recorded -= request
    system.log.info("\n" + request + "\n" + response)
    response
  }

  private val binding = Http().bind(interface = host, port = port)
                              .to(Sink.foreach(connection => connection handleWithSyncHandler requests))
                              .run()

  def record(tuple: (HttpRequest, HttpResponse)): Unit = {
    system.log.debug("Record: " + tuple)
    recorded += tuple
  }
}