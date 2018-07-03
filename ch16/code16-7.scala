// 코드 16-7 사용자 정의 우편함 테스트하기

val statProbe = TestProbe()
system.eventStream.subscribe(
  statProbe.ref,
  classOf[MailboxStatistics])
val testActor = system.actorOf(Props(
  new ProcessTestActor(1.second)), "monitorActor2")
statProbe.send(testActor, "message")
statProbe.send(testActor, "message2")
statProbe.send(testActor, "message3")
val stat = statProbe.expectMsgType[MailboxStatistics]

stat.queueSize must be (1)
val stat2 = statProbe.expectMsgType[MailboxStatistics]

stat2.queueSize must (be(2) or be(1))
val stat3 = statProbe.expectMsgType[MailboxStatistics]

stat3.queueSize must (be(3) or be(2))
