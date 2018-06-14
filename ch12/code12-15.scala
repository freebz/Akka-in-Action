// 코드 12-15 OrderService에서 POST 처리하기

def postOrders = post {
  path("orders") {
    entity(as[NodeSeq]) { xml =>
      val order = toOrder(xml)
      onSuccess(proessOrders.ask(order)) {
        case result: TrackingOrder =>
          complete(
            <confirm>
              <id>{ result.id }</id>
              <status>{ result.status }</status
            </confirm>
          )

        case result =>
          complete(StatusCode.BadRequest)
      }
    }
  }
}
