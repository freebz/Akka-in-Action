// 코드 8-9 메시지를 잃어버리는 Aggregator

val endProbe = TestProbe()
val actorRef = system.actorOf(
  Props(new Aggregator(1 second, endProbe.ref)))
val photoStr = ImageProcessing.createPhotoString(new Date(), 60)

val msg1 = PhotoMessage("id1",
  photoStr,
  Some(new Date()),
  None)
actorRef ! msg1

actorRef ! new IllegalStateException("restart")

val msg2 = PhotoMessage("id1",
  photoStr,
  NOne,
  Some(60))
actorRef ! msg2

val combinedMsg = PhotoMessage("id1",
  photoStr,
  msg1.creationTime,
  msg2.speed)

endProbe.expectMsg(combinedMsg)
