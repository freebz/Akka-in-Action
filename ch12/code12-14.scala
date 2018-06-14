// 코드 12-14 OrderService에서 orders/id GET 요청 처리하기

def getOrder = get {
  pathPrefix("orders" / IntNumber) { id =>
    onSuccess(processOrders.ask(OrderId(id))) {
      case result: TrackingOrder =>
        complete(
          <statusResponse>
            <id>{ result.id }</id>
            <status>{ result.status }</status>
          </statusResponse>)
      case result: NoSuchOrder =>
        complete(StatusCodes.NotFound)
    }
  }
}
