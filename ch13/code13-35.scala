// 코드 13-35 그래프 내의 버퍼

val archBuf = Flow[Event]
  .buffer(archBufSize, OverflowStrategy.fail)

val warnBuf = Flow[Event]
  .buffer(warnBufSize, OverflowStrategy.dropHead)

val errBuf = Flow[Event]
  .buffer(errBufSize, OverflowStrategy.backpressure)

val metricBuf = Flow[Event]
  .buffer(errBufSize, OverflowStrategy.dropHead)
