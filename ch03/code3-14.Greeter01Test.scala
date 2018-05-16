// 코드 3-14 Greeter 테스트하기

import Greeter01Test._

class Greeter01Test extends TestKit(testSystem)
    with WordSpecLike
    with StopSystemAfterAll {

  "The Greeter" must {
    "say Hello World! when a Greeting("World") is sent to it" in {
      val dispatcherId = CallingThresdDispatcher.Id
      val props = Props[Greeter].withDispatcher(dispatcherID)
      val greeter = system.actorOf(props)
      EventFilter.info(message = "Hello World!",
        occurences = 1).intercept {
        greeter ! Greeting("World")
      }
    }
  }
}

object Greeter01Test {
  val testSystem = {
    val config = ConfigFactory.parseString(
      """
        akka.loggers = [akka.testkit.TestEventListener]
      """)
    ActorSystem("testsystem", config)
  }
}
