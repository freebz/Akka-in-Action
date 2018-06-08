// 코드 11-5 진입 동작 구현

class Inventory(publisher:ActorRef) extends Actor
    with FSM[State, StateData] {

  startWith(WaitForRequest, new StateData(0,Seq()))

  when...

  onTransition {
    case _ -> WaitForRequests => {
      if (!nextStateData.pendingRequests.isEmpty) {
        // 다음 상태로 천이한다.
        self ! PendingRequests
      }
    }
    case _ -> WaitForPublisher => {
      val request = nextStateData.pendingRequests.head
      reserveId += 1
      request.target !
      new BookReply(request.context, Right(reserveId))
      self ! Done
    }
    case _ -> ProcessSoldOut => {
      nextStateData.pendingRequests.foreach(request => {
        request.target !
        new BookReply(request.context, Left("SoldOut"))
      })
      self ! Done
    }
  }
}
