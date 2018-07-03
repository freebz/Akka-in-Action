// 코드 16-9 MonitorActor 트레이트 테스트하기

val statProbe = TestProbe()
system.eventStream.subscribe(
  statProbe.ref,
  classOf[ActorStatistics])

val testActor = system.actorOf(Props(
  new ProcessTestActor(1.second) with MonitorActor)
  ,"monitorActor")

statProbe.send(testActor,"message")
val stat = statProbe.expectMsgType[ActorStatistics]
stat.exitTime -
      stat.entryTime must be (1000L plusOrMinus 10)
