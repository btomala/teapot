package btomala.http.mock.helpers

import akka.http.scaladsl.model.DateTime
import akka.http.scaladsl.model.headers._

import scala.collection.immutable

package object akkahttp {
  /** required headers when you are using akka-http client*/
  def headers(host: String = "localhost", port: Int = 80, userAgent:String  = "akka-http/2.3.11") =
    scala.collection.immutable.Seq(
      Host(host, port),
      `User-Agent`(userAgent)
    )

  def response: immutable.Seq[ModeledHeader] = scala.collection.immutable.Seq(Server("akka-http/2.3.11"), Date(DateTime.now))
}
