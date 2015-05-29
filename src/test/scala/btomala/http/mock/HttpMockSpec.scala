package btomala.http.mock

import akka.actor.ActorSystem
import akka.http.scaladsl.model._
import akka.stream.ActorFlowMaterializer
import akka.testkit.TestKit
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

import scala.concurrent.Await
import scala.concurrent.duration._

class HttpMockSpec extends TestKit(ActorSystem("teapot")) with test.TeapotSpec with WordSpecLike with Matchers with BeforeAndAfterAll {

  implicit val materializer = ActorFlowMaterializer()

  override def afterAll = system.shutdown()

  val timeout = 2 seconds

  val mockServer = new HttpMock()
  val mainPath = mockServer.path
  val serverPort = mockServer.port
    

  "Http Mock Server" when {
    "created" should {
      val port = 10080
      s"be running on port $port" in {
        mockServer.port shouldBe port
      }
    }
    "not have recorded any request" should {
      "respond `I'm a teapot`" in {
        val response = Await.result(request(HttpRequest(uri = Uri(mainPath))), timeout)
        response.status.intValue shouldBe 418
      }
    }
    "have recorded one request" should {
      "send recorded response" in {
        mockServer.record (akkaRequest → emptyResponse)
        val response = Await.result(request(HttpRequest(uri = Uri(mainPath))), timeout)
        response.status.intValue shouldBe emptyResponse.status.intValue
      }
      "return `I'm a teapot` if request didn't match " in {
        mockServer.record (HttpRequest() → emptyResponse)
        val response = Await.result(request(HttpRequest(uri = Uri(mainPath))), timeout)
        response.status.intValue shouldBe 418
      }
    }
  }

  val akkaRequest = HttpRequest(uri = Uri(mainPath), headers = helpers.akkahttp.headers(port = serverPort))
  val emptyResponse = HttpResponse()
}
