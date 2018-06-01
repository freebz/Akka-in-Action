// 코드 10-6 DeadLetter 메시지 보내기

val deadLetterMonitor = TestProbe()
val actor = system.actorOf(Props[EchosActor], "echo")

system.eventStream.subscribe(
  deadLetterMonitor.ref,
  classOf[DeadLetter]
)

val msg = new Order("me", "Akka in Action", 1)
val dead = DeadLetter(msg, testActor, actor)
system.deadLetters ! dead

deadLetterMonitor.expectMsg(dead)

system.stop(actor)
