package btomala.teapot.helpers

import akka.http.scaladsl.model._
import akka.http.scaladsl.model.headers._

package object dispatch {
  /** required headers when you are using dispatch client*/
  def headers(host: String = "localhost", port: Int = 80, userAgent: String = "Dispatch/0.11.1-SNAPSHOT") = scala.collection.immutable.Seq(
    Host(host, port),
    Connection("keep-alive"),
    Accept(Vector(MediaRanges.`*/*`)),
    `User-Agent`(userAgent)
  )
}
