package http.mock

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.ActorFlowMaterializer
import akka.stream.scaladsl.Sink
import com.typesafe.config.{ConfigFactory, Config}

class HttpMock(config: Config = ConfigFactory.load)(implicit system: ActorSystem, materializer: ActorFlowMaterializer) {

  val host: String = config.getString("mock.http.interface")
  val port: Int = config.getInt("mock.http.port")

  val requests: HttpRequest => HttpResponse = Map[HttpRequest, HttpResponse]()
  val binding = Http().bind(interface = host, port = port)
                      .to(Sink.foreach(connection => connection handleWithSyncHandler requests))
                      .run()
}