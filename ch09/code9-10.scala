// 코드 9-10 라우티를 관리하는 GetLicenseCreator 테스트하기

val endProbe = TestProbe()

val creator = system.actorOf(
  Props(new GetLicenseCreator2(2, endProbe.ref)),"Creator")
val paths = List(
  "/user/Creator/GetLicense0",
  "/user/Creator/GetLicense1")
val router = system.actorOf(
  RoundRobinGroup(paths).props(), "groupRouter")

router ! Broadcast(PoisonPill)
Thread.sleep(100)

val msg = PerformanceRoutingMessage(
        ImageProcessing.createPhotoString(new Date(), 60, "123xyz"),
        None,
        None)
// 라우티들이 응답하는지 테스트
router ! msg
endProbe.expectMsgType[PerformanceRoutingMessage](1 second)
