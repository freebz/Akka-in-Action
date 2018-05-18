// 코드 5-24 한 번 더 foldLeft를 사용해 조합하기

val ticketInfos = List(infowithTravelAdvice, infoWithWeather)

val infoWithTravelAndWeather: Future[TicketInfo] =
  Future.foldLeft(ticketInfos)(info) {
    (acc, elem) =>
      val (travelAdvice, weather) = (elem.travelAdvice, elem.weather)
            acc.copy(
              travelAdvice = travelAdvice.orElse(acc.travelAdvice),
              weather = weather.orElse(acc.weather)
            )
  }
