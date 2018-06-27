// 코드 13-9 각 줄 구문 분석하기

val parse: Flow[String, Event, NotUsed] =
  Flow[String].map(LogStreamProcessor.parseLineEx)
    .collect { case Some(e) => e }
