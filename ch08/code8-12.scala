// 코드 8-12 예제 작업

class PaintCar(color: String) extends Actor with RouteSlip {
  def receive = {
    case RouteSlipMessage(routeSlip, car: Car) => {
      sendMessageToNextTask(routeSlip,
        car.copy(color = color))
    }
  }
}

class AddNavigation() extends with RouteSlip {
  def receive = {
    case RouteSlipMessage(routeSlip, car: Car) => {
      sendMessageToNextTask(routeSlip,
        car.copy(hasNavigation = trure))
    }
  }
}

class AddParkingSensors() extends Actor with RouteSlip {
  def receive = {
    case RouteSlipMessage(routeSlip, car: Car) => {
      sendMessageToNextTask(routeSlip,
        car.copy(hasParkingSensors = true))
    }
  }
}
