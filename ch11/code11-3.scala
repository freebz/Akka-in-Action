// 코드 11-3 whenUnhandled로 기본 동작 구현하기

whenUnhandled {
  // 모든 상태에 공통 적용되는 코드
  case Event(request:BookRequest, data:StateData) => {
    stay using data.copy(
      pendingRequests = data.pendingRequests :+ request)
  }
  case Event(e, s) => {
    log.warning("received unhandled request {} in state {}/{}",
      e, stateName, s)
    stay
  }
}
