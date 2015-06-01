package btomala.teapot.test

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse, StatusCode}
import akka.http.{ClientConnectionSettings, ConnectionPoolSettings, ParserSettings}
import akka.stream.FlowMaterializer
import btomala.teapot.status.`I'mATeapot`

import scala.concurrent.Future

trait TeapotSpec {

  /**
   * akka-http client request with handle response 418
   */
  def make(request: HttpRequest)(implicit system: ActorSystem, materializer: FlowMaterializer): Future[HttpResponse] = {
    val customCode: Int ⇒ Option[StatusCode] = {case 418 ⇒ Some(`I'mATeapot`)}
    val parserSettings= ParserSettings(system).copy(customStatusCodes = customCode)
    val clientSettings = ClientConnectionSettings(system).copy(parserSettings = parserSettings)
    val poolSettings = ConnectionPoolSettings(system).copy(connectionSettings = clientSettings)
    Http().singleRequest(request, settings = poolSettings)
  }

}
