// 코드 11-2 WaitForRequests의 처리

when(WaitForRequests) {
  case Event(request:BookRequest, data:StateData) => {
    val newStateData = data.copy(
      pendingRequests = data.pendingRequests :+ request)
    if (newStateData.nrBooksInStore > 0) {
      goto(ProcessRequest) using newStateData
    } else {
      goto(WaitForPublisher) using newStateData
    }
  }
  case Event(PendingRequests, data:StateData) => {
    if (data.pendingRequests.isEmpty) {
      stay
    } else if(data.nrBooksInStore > 0) {
      goto(ProcessRequest)
    } else {
      goto(WaitForPublisher)
    }
  }
}
