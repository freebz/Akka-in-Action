// 코드 8-11 메시지 라우팅하기

case class RouteSlipMessage(routeSlip: Seq[ActorRef],
                            message: AnyRef)

trait RouteSlip {

  def sendMessageToNextTask(routeSlip: Seq[ActorRef],
                            message: AnyRef): Unit = {
    val nextTask = routeSlip.head
    val newSlip = routeSlip.tail
    if (newSlip.isEmpty) {
      nextTask ! message
    } else {

      nextTask ! RouteSlipMessage(
        routeSlip = newSlip,
        message = message)
    }
  }
}
