// 코드 13-4 RunnableGraph를 실행해서 스트림 복사하기

implicit val system = ActorSystem()
implicit val ec = system.dispatcher
implicit val materializer = ActorMaterializer()

runnableGraph.run().foreach { result =>
  println(s"${result.status}, ${result.count} bytes read.")
  system.terminate()
}
