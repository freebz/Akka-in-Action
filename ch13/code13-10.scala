// 코드 13-10 이벤트 필터링하기

val filter: Flow[Event, Event, NotUsed] =
  Flow[Event].filter(_.state == filterState)
