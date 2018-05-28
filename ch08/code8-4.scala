// 코드 8-4 수신자 리스트 테스트

val endProbe1 = TestProbe()
val endProbe2 = TestProbe()
val endProbe3 = TestProbe()
val list = Seq(endProbe1.ref, endProbe2.ref, endProbe3.ref)
val actorRef = system.actorOf(
  Props(new RecipientList(list)))
val msg = "message"
actorRef ! msg
endProbe1.expectMsg(msg)
endProbe2.expectMsg(msg)
endProbe3.expectMsg(msg)
