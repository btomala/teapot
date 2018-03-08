package btomala.teapot

import akka.http.scaladsl.model.HttpProtocols.{`HTTP/1.0`, `HTTP/1.1`}
import akka.http.scaladsl.model.StatusCodes.ImATeapot
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpProtocol, HttpResponse}
import akka.util.ByteString
import btomala.teapot.headers.default

package object response {
  val delimited = HttpEntity.Strict(ContentTypes.`application/octet-stream`, ByteString())
  /** response for `HTTP/1.1` protocol with stubbed date */
  lazy val teapot = HttpResponse(ImATeapot, protocol = `HTTP/1.1`, headers = default.response, entity = delimited)
  /** response for `HTTP/1.0` protocol with stubbed date*/
  lazy val oldTeapot = HttpResponse(ImATeapot, protocol = `HTTP/1.0`, headers = default.response)
  /** response depend on `HttpProtocol`*/
  def teapotResponse(protocol: HttpProtocol) =  if(protocol == `HTTP/1.0`) oldTeapot else teapot
}
