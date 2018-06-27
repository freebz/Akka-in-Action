// 코드 13-17 필터 흐름과 BidiFlow를 조합하기

val filter: Flow[Event, Event, NotUsed] =
  Flow[Event].filter(_.state == filterState)

val flow = bidiFlow.join(filter)
