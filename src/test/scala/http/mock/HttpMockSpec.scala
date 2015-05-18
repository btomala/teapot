package http.mock

import akka.actor.ActorSystem
import akka.http.scaladsl.model.{HttpResponse, HttpRequest}
import akka.stream.ActorFlowMaterializer
import akka.testkit.TestKit
import com.typesafe.config.{Config, ConfigFactory}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

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
}
