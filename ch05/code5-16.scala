// 코드 5-16 firstCompletedOf 메서드로 가장 빠른 응답 얻기

def getWeather(ticketInfo: TicketInfo): Future[TicketInfo] = {

  val futureWeatherX: Future[Option[Weather]] =
    callWeatherXService(ticketInfo).recover(withNone)

  val futureWeatherY: Future[Option[Weather]] =
    callWeatherYService(ticketInfo).recover(withNone)

  val futures: List[Future[Option[Weather]]] =
    List(futureWeatherX, futureWeatherY)

  val fastestResponse: Future[Option[Weather]] =
    Future.firstCompletedOf(futures)

  fastestResponse.map { weatherResponse =>
    ticketInfo.copy(weather = weatherResponse)
  }
}
