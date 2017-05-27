package btomala.teapot

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.headers.`Timeout-Access`
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.stream.Materializer
import akka.stream.scaladsl.Sink
import com.typesafe.config.{Config, ConfigFactory}
import response.teapotResponse

class HttpMock(config: Config = ConfigFactory.load)(implicit system: ActorSystem, materializer: Materializer) {

  val host = config.getString("mock.http.interface")
  val port = config.getInt("mock.http.port")
  val path = s"http://$host:$port/"

  private val recorded = scala.collection.concurrent.TrieMap[HttpRequest, HttpResponse]()

  private val requests: HttpRequest => HttpResponse = { request ⇒

    //FIXMe workaround for `timeout-access`
    val newRequest = request.copy(headers = request.headers.filterNot(heder => heder.is(`Timeout-Access`.name.toLowerCase)))

    val response = recorded.getOrElse(newRequest, teapotResponse(newRequest.protocol))
    recorded -= newRequest
    system.log.info("\n" + newRequest + "\n" + response)
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