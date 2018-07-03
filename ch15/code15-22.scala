// 코드 15-22 장바구니 이벤트의 라이브 스트림 얻기

val src: Source[EventEnvelope, NotUsed] =
  queries.eventsByPersistenceId(
    Basket.name(shopperId), 0L, Long.MaxValue)

val dbRows: Source[DbRow, NotUsed] =
    src.map(eventEnvelope => toDbRow(eventEnvelope))
  events.runWith(reactiveDatabaseSink)
