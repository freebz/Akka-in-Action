// 코드 5-19 zip과 map을 사용해 경로와 대중교통 정보 조합하기

def getTravelAdvice(info:TicketInfo,
                    event:Event): Future[TicketInfo] = {

  val futureR: Future[Option[BouteByCar]] = callTraffic(
    info.userLocation,
    event.location,
    event.time
  ).recover(withNone)

  val futureP: Future[Option[PublicTransportAdvice]] =
    callPublicTransport(info.userLocation,
      event.location,
      event.time
    ).recover(withNone)

  futureR.zip(futureP)
    .map {
      case(routeByCar, publicTransportAdvice) =>
        val travelAdvice = TravelAdvice
                              (routebyCar,
                               publicTransportAdvice)
        info.copy(travelAdvice = Some(travelAdvice))
    }
}
