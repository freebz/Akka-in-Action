// 코드 11-4 다른 상태로의 천이 구현

when(WaitForPublisher) {
  case Event(supply:BookSupply, data:StateData) => {
    goto(ProcessRequest) using data.copy(
      nrBooksInStore = supply.nrBooks)
  }
  case Event(BookSupplySoldOut, _) => {
    goto(ProcessSoldOut)
  }
}
when(ProcessRequest) {
  case Event(Done, data:StateData) => {
    goto(WaitForRequest) using data.copy(
      nrBooksInStore = data.nrBooksInStore - 1,
      pendingRequests = data.pendingRequests.tail)
  }
}
when(SoldOut) {
  case Event(request:BookRequest, data:StateData) => {
    goto(ProcessSoldOut) using new StateData(0,Seq(request))
  }
}
when(ProcessSoldOut) {
  case Event(Done, data:StateData) => {
    goto(SoldOut) using new StateData(0,Seq())
  }
}
