// 코드 5-13 recover를 사용해 다른 퓨처를 결과로 돌려주기

val futureStep1: Future[TicketInfo] = getEvent(ticketNr)
val futureStep2: Future[TicketInfo] = futureStep1.flatMap { ticketInfo =>
  getTraffic(ticketInfo).recover {
    case _: TrafficServiceException => ticketInfo
  }
}
