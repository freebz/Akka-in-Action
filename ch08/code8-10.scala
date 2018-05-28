// 코드 8-10 분배-취합 패턴 구현

val endProbe = TestProbe()
val aggregateRef = system.actorOf(
  Props(new Aggregator(1 seconds, endProbe.ref)))
val speedRef = system.actorOf(
  Props(new GetSpeed(aggregateRef)))
val timeRef = system.actorOf(
  Props(new GetTime(aggregateRef)))
val actorRef = system.actorOf(
  Props(new RecipientList(Seq(speedRef, timeRef))))

val photoDate = new Date()
val photoSpeed = 60
val msg = PhotoMessage("id1",
  ImageProcessing.createPhotoString(photoDate, photoSpeed))

actorRef ! msg

val combinedMsg = PhotoMessage(msg.id,
  msg.photo,
  Some(photoDate),
  Some(photoSpeed))

endProbe.expectMsg(combinedMsg)
