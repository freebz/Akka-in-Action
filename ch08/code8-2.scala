// 코드 8-2 파이프와 필터 테스트

val endProbe = TestProbe()
val speedFilterRef = system.actorOf(
  Props(new SpeedFilter(50, endProbe.ref)))
val licenseFilterRef = system.actorOf(
  Props(new LicenseFilter(speedfilterRef)))
val msg = new Photo("123xzy", 60)
licenseFilterRef ! msg
endProbe.expectMsg(msg)

licenseFilterRef ! new Photo("", 60)
endProbe.expectNoMsg(1 second)

licenseFilterRef ! new Photo("123xyz", 49)
endProbe.expectNoMsg(1 second)
