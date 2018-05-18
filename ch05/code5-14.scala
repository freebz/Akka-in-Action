// 코드 5-14 recover를 사용해 getEvent가 실패했을 때도 빈 TicketInfo 반환하기

val futureStep1: Future[TicketInfo] = getEvent(ticketNr)

val futureStep2: Future[TicketInfo] = futureStep1.flatMap { ticketInfo =>
  getTraffic(ticketInfo).recover {
    case _:TrafficServiceException => ticketInfo
  }
}.recover {
  case e => TicketInfo(ticketNr)
}
