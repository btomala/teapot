package btomala.teapot

import akka.actor.ActorSystem
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import akka.testkit.TestKit
import btomala.teapot.response.teapot
import btomala.teapot.headers.default
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.collection.immutable.Seq

class HttpMockSpec extends TestKit(ActorSystem("teapot")) with test.TeapotSpec with WordSpecLike with Matchers with BeforeAndAfterAll {

  implicit val materializer = ActorMaterializer()

  override def afterAll() = system.shutdown()

  val timeout = 2 seconds

  val mockServer = new HttpMock()
  val mainPath = mockServer.path
  val serverPort = mockServer.port

  "Http Mock Server" when {
    "not have recorded any request" should {
      "respond `I'm a teapot`" in {
        val response = Await.result(make(HttpRequest(uri = Uri(mainPath))), timeout)
        response.status.intValue shouldBe 418
      }
    }
    "have recorded one request" should {
      "send recorded empty response with additional headers (Server, Date)" in {
        mockServer.record (akkaDefaultRequest → emptyResponse)
        val response = Await.result(make(HttpRequest(uri = Uri(mainPath))), timeout)
        response.copy(headers = Seq.empty[HttpHeader]) shouldBe emptyResponse
      }
      "send recorded response" in {
        mockServer.record (akkaDefaultRequest → defaultResponse)

        val response = Await.result(make(HttpRequest(uri = Uri(mainPath))), timeout)

        response shouldBe defaultResponse
      }
      "return `I'm a teapot` if request didn't match " in {
        mockServer.record (HttpRequest() → emptyResponse)
        val response = Await.result(make(HttpRequest(uri = Uri(mainPath))), timeout)
        response shouldBe teapot
      }
    }
  }

  val akkaDefaultRequest = HttpRequest(uri = Uri(mainPath), headers = default.akkahttp(port = serverPort))
  val emptyResponse = HttpResponse()
  val defaultResponse = HttpResponse(headers = default.response)

}
