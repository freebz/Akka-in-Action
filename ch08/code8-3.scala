// 코드 8-3 필터의 순서를 바꿈

val endProbe = TestProbe()
val licenseFilterRef = system.actorOf(
  Props(new LicenseFilter(endProbe(endProbe.ref)))
val speedFilterRef = system.actorOf(
  Props(new SpeedFilter(50, licenseFilterRef)))
val msg = new Photo("123xzy", 60)
speedfilterRef ! msg
endProbe.expectMsg(msg)

speedfilterRef ! new Photo("", 60)
endProbe.expectNoMsg(1 second)

speedfilterRef ! new Photo("123xyz", 49)
endProbe.expectNoMsg(1 second)
