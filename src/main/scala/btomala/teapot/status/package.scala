package btomala.teapot

import akka.http.scaladsl.model.StatusCodes

package object status {
  /** `HttpStatus` for all misunderstand request to the mock server*/
  val `I'mATeapot` = StatusCodes.custom(418, "I'm a teapot", "The server cannot meet the recorded request.", isSuccess = false, allowsEntity = false)
}
