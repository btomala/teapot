package btomala.teapot

import akka.http.scaladsl.model.HttpProtocols.`HTTP/1.0`
import akka.http.scaladsl.model.{HttpProtocol, HttpResponse}
import btomala.teapot.headers.default
import btomala.teapot.status.`I'mATeapot`

package object response {
  /** response for `HTTP/1.1` protocol with stubbed date */
  lazy val teapot = HttpResponse(`I'mATeapot`, headers = default.response)
  /** response for `HTTP/1.0` protocol with stubbed date*/
  lazy val oldTeapot = HttpResponse(`I'mATeapot`, protocol = `HTTP/1.0`, headers = default.response)
  /** response depend on `HttpProtocol`*/
  def teapotResponse(protocol: HttpProtocol) =  if(protocol == `HTTP/1.0`) oldTeapot else teapot
}
