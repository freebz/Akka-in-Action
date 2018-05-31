// 코드 9-14 상태를 사용하는 SwitchRouter 액터 테스트하기

val normalFlowProbe = TestProbe()
val cleanupProbe = TestProbe()
val router = system.actorOf(
  Props(new SwitchRouter(
    normalFlow = normalFlowProbe.ref,
    cleanUp = cleanupProbe.ref)))

val msg = "message"
router ! msg

cleanupProbe.expectMsg(msg)
normalFlowProbe.expectNoMsg(1 second)

router ! RouteStateOn

router ! msg

cleanupProbe.expectNoMsg(1 second)
normalFlowProbe.expectMsg(msg)

router ! RouteStateOff
router ! msg

cleanupProbe.expectMsg(msg)
normalFlowProbe.expectNoMsg(1 second)
