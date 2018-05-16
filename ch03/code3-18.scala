// 코드 3-18 메아리(echo) 액터 테스트하기

"Reply with the same message it receives without ask" in {
  val echo = system.actorOf(Props[EchoActor], "echo2")
  echo ! "some message"
  expectMsg("some message")
}
