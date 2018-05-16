// 코드 3-16 더 간단한 Greeter 액터 테스트

class Greeter02Test extends TestKit(ActorSystem("testsystem"))
    with WordSpecLike
    with StopSystemAfterAll {

  "The Greeter" must {
    "say Hello World! when a Greeting("World") is sent to it" in {
      val props = Greeter02.props(Some(testActor))
      val greeter = system.actorOf(props, "greeter02-1")
      greeter ! Greeting("World")
      expectMsg("Hello World")
    }
    "say something else and see what happens" in {
      val props = Greeter02.props(Some(testActor))
      val greeter = system.actorOf(props, "greeter02-2")
      system.eventStream.subscribe(testActor, classOf[UnhandledMessage])
      greeter ! "World"
      expectMsg(UnhandledMessage("World", system.deadLetters, greeter))
    }
  }
}
