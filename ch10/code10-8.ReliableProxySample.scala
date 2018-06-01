// 코드 10-8 ReliableProxySample 테스트 환경 만들기

import scala.concurrent.duration._
import concurrent.Await
import akka.contrib.pattern.ReliableProxy
import akka.remote.testconductor.Direction

class ReliableProxySample
    extends MultiNodeSpec(ReliableProxySampleConfig)
    with STMultiNodeSpec
    with ImplicitSender {

  import ReliableProxySampleConfig._

  def initialParticipants = roles.size

  "A MultiNodeSample" must {

    "wait for all nodes to enter a barrier" in {
      enterBarrier("startup")
    }

    "send to and receive from a remote node" in {
      runOn(client) {
        enterBarrier("deployed")
        val pathToEcho = node(server) / "user" / "echo"
        val echo = system.actorSelection(pathToEcho)
        val proxy = system.actorOf(
          Props(new ReliableProxy(pathToEcho, 500.millis)), "proxy")

        ... 실제 테스트를 진행한다
      }

      runOn(server) {
        system.actorOf(Props(new Actor {
          def receive = {
            case msg:AnyRef => {
              sender ! msg
            }
          }
        }), "echo")
        enterBarrier("deployed")
      }

      enterBarrier("finished")
    }
  }
}
