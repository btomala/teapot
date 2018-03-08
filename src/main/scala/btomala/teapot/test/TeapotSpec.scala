package btomala.teapot.test

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}

import scala.concurrent.Future

trait TeapotSpec {

  /**
   * akka-http client request with handle response 418
   */
  def make(request: HttpRequest)(implicit system: ActorSystem): Future[HttpResponse] = {
    Http().singleRequest(request)
  }

}
