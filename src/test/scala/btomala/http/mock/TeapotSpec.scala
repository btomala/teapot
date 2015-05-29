package btomala.http.mock

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.ActorFlowMaterializer
import akka.stream.scaladsl.Sink
import akka.testkit.TestKit
import org.scalatest.{Matchers, WordSpecLike}

import scala.concurrent.Await
import scala.concurrent.duration._

class TeapotSpec extends TestKit(ActorSystem("teapot")) with test.TeapotSpec with WordSpecLike with Matchers {

  implicit val materializer = ActorFlowMaterializer()

  val bindings = Http().bind(interface = "localhost", port = 7777)
                       .to(Sink.foreach(connection => connection handleWithSyncHandler (_ ⇒ HttpResponse(`I'mATeapot`))))
                       .run()

  "No exception will be thrown" when {
    "during request & 'I'm a teapot' status will be returned"  in {
      val future = request(HttpRequest(uri = Uri("http://localhost:7777/")))
      val resp = Await.result(future, 1 second)
      resp.status.intValue should be (418)
    }
  }
}
