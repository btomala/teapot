package http.mock

import akka.actor.ActorSystem
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.headers._
import akka.stream.ActorFlowMaterializer
import akka.testkit.TestKit
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

import dispatch._
import Defaults._

import scala.concurrent.Await
import scala.concurrent.duration._

class HttpMockSpec extends TestKit(ActorSystem("ServiceTest")) with WordSpecLike with Matchers with BeforeAndAfterAll {

  override def afterAll = system.shutdown()
  implicit val materializer = ActorFlowMaterializer()

  val port = 10080

  val mockServer = new HttpMock()
  "Http Mock Server" should {
    s"be running on port $port" in {
      mockServer.port shouldBe port
    }
  }

  val address = s"http://localhost:$port/"
  val headers = scala.collection.immutable.Seq(
    Host("localhost", port),
    Connection("keep-alive"),
    Accept(Vector(MediaRanges.`*/*`)),
    `User-Agent`("Dispatch/0.11.1-SNAPSHOT")
  )

  val request = HttpRequest(uri = Uri(address), headers = headers)
  val response = HttpResponse()

  "Http Mock Server" when {
    "have recorded one request" should {
      "send recorded response" in {
        mockServer.record (request → response)
        val res = Await.result(Http(url(address)), 2 seconds)
        res.getStatusCode shouldBe response.status.intValue
      }
    }
  }

  "Http Mock Server" when {
    "not have recorded any request" should {
      "return InternalServerError " in {
        val res = Http(url(address)).map(_.getStatusCode)
        Await.result(res, 10 seconds) shouldBe 500
      }
    }
  }

  "Http Mock Server" when {
    "have recorded some request but request didn't match" should {
      "throw InternalServerError" in {
        mockServer.record (HttpRequest() → response)
        val res = Http(url(address)).map(_.getStatusCode)
        Await.result(res, 2 seconds) shouldBe 500
      }
    }
  }
}
