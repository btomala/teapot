package btomala.http.mock.helpers

import akka.http.scaladsl.model._
import akka.http.scaladsl.model.headers._

package object dispatch {
  //todo port should be optional ??
  def path(port: Int) = s"http://localhost:$port/"
  def headers(port: Int) = scala.collection.immutable.Seq(
    Host("localhost", port),
    Connection("keep-alive"),
    Accept(Vector(MediaRanges.`*/*`)),
    `User-Agent`("Dispatch/0.11.1-SNAPSHOT")
  )
  def request(port: Int) = HttpRequest(uri = Uri(path(port)), headers = headers(port))
}
