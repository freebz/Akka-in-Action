// 코드 4-9 슈퍼바이저 계층 구조 구축하기

object LogProcessingApp extends App {
  val sources = Vector("file:///source1/", "file:///source2/")
  val system = ActorSystem("logprocessing")
  val databaseUrls = Vector(
    "http://mydatabase1",
    "http://mydatabase2",
    "http://mydatabase3"
  )

  system.actorOf(
    LogProcessingSupervisor.props(sources, databaseUrls),
    LogProcessingSupervisor.name
  )
}
