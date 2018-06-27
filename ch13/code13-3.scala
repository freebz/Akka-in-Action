// 코드 13-3 스트림 복사를 위해 RunnableGraph 정의하기

val source: Source[ByteString, Future[IOResult]] =
  FileIO.fromPath(inputFile)

val sink: Sink[ByteString, Future[IOResult]] =
  FileIO.toPath(outputFile, Set(CREATE, WRITE, APPEND))

val runnableGraph: RunnableGraph[Future[IOResult]] =
  source.to(sink)
