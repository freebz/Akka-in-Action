// 코드 8-14 기본 사양 차 만들기

val probe = TestProbe()
val router = system.actorOf(
  Props(new SlipRouter(probe.ref)), "SlipRouter")

val minimalOrder = new Order(Seq())
router ! minimalOrder
val defaultCar = new Car(
  color = "black",
  hasNavigation = false,
  hasParkingSensors = false)
prob.expectMsg(defaultCar)
