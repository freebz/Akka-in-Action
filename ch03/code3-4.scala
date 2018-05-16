// 코드 3-4 단일 스레드 테스트 내부 상태

"change internal state when it receives a message, single" in {
  import SilentActor._

  val silentActor = TestActorRef[SilentActor]

  silentActor ! SilentMessage("whisper")
  silentActor.underlyingActor.state must (contain("whisper"))
}
