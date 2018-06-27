// 코드 13-12 조합한 이벤트 필터 흐름

val composedFlow: Flow[ByteString, ByteString, NotUsed] =
  frame.via(parse)
    .via(filter)
    .via(serialize)

val runnableGraph: RunnableGraph[Future[IOResult]] =
  source.via(composedFlow).toMat(sink)(Keep.right)

runnableGraph.run().foreach { result =>
  println(s"Wrote ${result.count} bytes to '$outputFile'.")
  system.terminate()
}
