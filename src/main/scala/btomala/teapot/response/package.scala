package btomala.teapot

import akka.http.scaladsl.model.HttpProtocols.`HTTP/1.0`
import akka.http.scaladsl.model.{HttpProtocol, HttpResponse}
import btomala.teapot.status.`I'mATeapot`

package object response {
  lazy val teapot = HttpResponse(`I'mATeapot`)
  lazy val oldTeapot = HttpResponse(`I'mATeapot`, protocol = `HTTP/1.0`)
  def teapotResponse(protocol: HttpProtocol) =  if(protocol == `HTTP/1.0`) oldTeapot else teapot
}
