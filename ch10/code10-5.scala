// 코드 10-5 배달할 수 없는 메시지 잡아내기

val deadLetterMonitor = TestProbe()

system.eventStream.subscribe(
  deadLetterMonitor.ref,
  classOf[DeadLetter]
)

val actor = system.actorOf(Props[EchoActor], "echo")
actor ! PoisonPill
val msg = new Order("me", "Akka in Action", 1)
actor ! msg

val dead = deadLetterMonitor.expectMsgType[DeadLetter]
dead.message must be(msg)
dead.sender must be(testActor)
dead.recipient must be(actor)
