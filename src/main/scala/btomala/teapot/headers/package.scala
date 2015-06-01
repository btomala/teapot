package btomala.teapot.headers

import akka.http.scaladsl.model.{DateTime, MediaRanges}
import akka.http.scaladsl.model.headers._

import scala.collection.immutable

package object default {

  /** required headers when you are using akka-http client*/
  def akkahttp(host: String = "localhost", port: Int = 80, userAgent:String  = "akka-http/2.3.11") =
    scala.collection.immutable.Seq(
      Host(host, port),
      `User-Agent`(userAgent)
    )

  /** required headers when you are using dispatch client*/
  def dispatch(host: String = "localhost", port: Int = 80, userAgent: String = "Dispatch/0.11.1-SNAPSHOT") =
    scala.collection.immutable.Seq(
      Host(host, port),
      Connection("keep-alive"),
      Accept(Vector(MediaRanges.`*/*`)),
      `User-Agent`(userAgent)
    )

  def response: immutable.Seq[ModeledHeader] =
    scala.collection.immutable.Seq(
      Server("akka-http/2.3.11"),
      Date(DateTime.now)
    )
}
