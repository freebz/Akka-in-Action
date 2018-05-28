// 코드 8-13 SlipRouter

class SlipRouter(endStep: ActorRef) extends actor with RouteSlip {
  val paintBlack = context.actorOf(
    Props(new PaintCar("black")), "paintBlack")
  val paintGray = context.actorOf(
    Props(new PaintCar("gray")), "paintGray")
  val addNavigation = context.actorOf(
    Props[AddNavigation], "navigation")
  val addParkingSensor = context.actorOf(
    Props[AddParkingSensors], "parkingSensors")

  def receive = {
    case order: Order => {
      val routeSlip = createRouteSlip(order.options)

      sendMessageToNextTask(routeSlip, new Car)
    }
  }

  private def createRouteSlip(options: Seq[CarOptions.Value]):
      Seq[ActorRef] = {

    val routeSlip = new ListBuffer[ActorRef]
    // 자동차의 색을 지정해야 한다.
    if (!options.contains(CarOptions.CAR_COLOR_GRAY)) {
      routeSlip += paintBlack
    }
    options.foreach {
      case CarOptios.CAR_COLOR_GRAY   => routeSlip += paintGray
      case CarOptions.NAVIGATION      => routeSlip += addNavigation
      case CarOptions.PARKING_SENSORS => routeSlip += addParkingSensor
      case other                      => // 아무 일도 하지 않는다.
    }
    routeSlip += endStep
    routeSlip
  }
}
