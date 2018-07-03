// 코드 15-21 현재의 장바구니 이벤트 얻기

val src: Source[EventEnvelope, NotUsed] =
  queries.currentEventsByPersistenceId(
    Basket.name(shopperId), 0L, Long.MaxValue)

val events: Source[Basket.Event, NotUsed] =
  src.map(_.event.asInstanceOf[Basket.Event])

val res: Future[Seq[Basket.Event]] = events.runWith(Sink.seq)
