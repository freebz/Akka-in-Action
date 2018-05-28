// 코드 8-15 모든 선택 사양이 적용된 차 만들기

val fullOrder = new Order(Seq(
  CarOptions.CAR_COLOR_GRAY,
  CarOptions.NAVIGATION,
  CarOptions.PARKING_SENSORS))
router ! fullOrder
val carWithAllOptions = new Car(
  color = "gray",
  hasNavigation = true,
  hasParkingSensors = true)
probe.expectMsg(carWithAllOptions)
