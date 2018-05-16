// 코드 3-6 다중 스레드 테스트 내부 상태

"change internal state when it receives a message, multi" in {
  import SilentActor._

  val silentActor = system.actorOf(Props[SilentActor], "s3")
  silentActor ! SilentMessage("whisper1")
  silentActor ! silentMessage("whisper2")
  silentActor ! GetState(testActor)
  expectMsg(Vector("whisper1", "whisper2"))
}
