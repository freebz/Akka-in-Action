// 코드 8-6 병합기 테스트

val endProbe = TestProbe()
val actorRef = system.actorOf(
  Props(new Aggregator(1 second, endProbe.ref)))
val photoStr = ImageProcessing.createPhotoString(new Date(), 60)
val msg1 = PhotoMessage("id1",
  photoStr,
  Some(new Date()),
  None)
actorRef ! msg1

val msg2 = PhotoMessage("id1",
  photoStr,
  None,
  Some(60))
actorRef ! msg2

val combineMsg = PhotoMessage("id1",
  photoStr,
  msg1.creationTime,
  msg2.speed)

endProbe.expectMsg(combinedMsg)
