package btomala.teapot.test

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.http.scaladsl.settings.{ClientConnectionSettings, ConnectionPoolSettings, ParserSettings}
import akka.stream.Materializer
import btomala.teapot.status.`I'mATeapot`

import scala.concurrent.Future

trait TeapotSpec {

  /**
   * akka-http client request with handle response 418
   */
  def make(request: HttpRequest)(implicit system: ActorSystem, materializer: Materializer): Future[HttpResponse] = {
    val parserSettings: ParserSettings = ParserSettings(system).withCustomStatusCodes(`I'mATeapot`)
    val clientSettings = ClientConnectionSettings(system).withParserSettings(parserSettings)
    val poolSettings = ConnectionPoolSettings(system).withConnectionSettings(clientSettings)
    Http().singleRequest(request, settings = poolSettings)
  }

}
