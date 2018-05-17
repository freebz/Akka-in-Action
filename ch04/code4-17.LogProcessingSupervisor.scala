// 코드 4-17 LogProcessingSupervisor

object LogProcessingSupervisor {
  def props(sources: Vector[String], databaseUrls: Vector[String]) =
    Props(new LogProcessingSupervisor(sources, databaseUrls))
  def name = "file-watcher-supervisor"
}

class LogProcessingSupervisor(
  sources: Vector[String],
  databaseUrls: Vector[String]
) extends Actor with ActorLogging {

  var fileWatchers: Vector[ActorRef] = sources.map { source =>
    val fileWatcher = context.actorOf(
      Props(new FileWatcher(source, databaseUrls))
    )
    context.watch(fileWatcher)
    fileWatcher
  }

  override def supervisorStrategy = AllForOneStrategy() {
    case _: DiskError => Stop
  }

  def receive = {
    case Terminated(fileWatcher) =>
      fileWatchers = fileWatchers.filterNot(_ == fileWatcher)
      if (fileWatchers.isEmpty) {
        log.info("Shutting down, all file watchers have failed.")
        context.system.terminate()
      }
  }
}
