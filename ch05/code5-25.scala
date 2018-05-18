// 코드 5-25 완전한 TicketInfoService 흐름

def getTicketInfo(ticketNr:String,
                  location:Location):Future[TicketInfo] = {
  val emptyTicketInfo = TicketInfo(ticketNr, location)
  val eventInfo = getEvent(ticketNr, location)
                   .recover(withPrevious(emptyTicketInfo))

  eventInfo.flatMap { info =>

    val infoWithWeather = getWeather(info)

    val infoWithTravelAdvice = info.event.map { event =>
      getTravelAdvice(info, event)
    }.getOrElse(eventInfo)

    val suggestedEvents = info.event.map { event =>
      getSuggestions(event)
    }.getOrElse(Future.successful(Seq()))

    val ticketInfos = List(infoWithTravelAdvice, infoWithWeather)

    val infoWithTravelAndWeather = Future.foldLeft(ticketInfos)(info) { (acc, elem) =>
      val (travelAdvice, weather) = (elem.travelAdvice, elem.weather)

      acc.copy(travelAdvice = travelAdvice.orElse (acc.travelAdvice),
           weather = weather.orElse(acc.weather))
    }

    for(info <- infoWithTravelAndWeather;
      suggestions <- suggestedEvents
    ) yield info.copy(suggestions = suggestions)
  }
}

// 복사 후 붙여넣기를 최소화하기 위한 오류 복구 함수들
type Recovery[T] = PartialFunction[Throwable,T]

// None으로 복구하기
def withNone[T]:Recovery[Option[T]] = {
  case e => None
}

// 빈 시퀀스로 복구하기
def withEmptySeq[T]:Recovery[Seq[T]] = {
  case e => Seq()
}

// 앞 단계에서 정의된 TicketInfo 값으로 복구하기
def withPrevious(previous:TicketInfo):Recovery[TicketInfo] = {
  case e => previous
}
