package btomala.http

import akka.http.scaladsl.model._

package object mock {
  val `I'mATeapot`   = StatusCodes.custom(418, "I'm a teapot", "The server cannot meet the recorded request.", isSuccess = false, allowsEntity = false)
  def notRecordedResponse(protocol: HttpProtocol) = HttpResponse(`I'mATeapot`, protocol = protocol)
}