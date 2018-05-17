// 코드 4-16 LogProcessor를 감독하는 FileWatcher

class FileWatcher(source: String,
                  databaseUrls: Vector[String])
    extends Actor with ActorLogging with FileWatchingAbilities {
  register(source)

  override def supervisorStrategy = OneForOneStrategy() {
    case _: CorruptedFileException => Resume
  }

  val logProcessor = context.actorOf(
    LogProcessor.props(databaseUrls),
    LogProcessor.name
  )
  context.watch(logProcessor)

  import FileWatcher._

  def receive = {
    case NewFile(file, _) =>
      logProcessor ! LogProcessor.LogFile(file)
    case SourceAbandoned(uri) if uri == source =>
      log.info(s"$uri abandoned, stopping file watcher.")
      self ! PoisonPill
    case Terminated(`logProcessor`) =>
      log.info(s"Log processor terminated, stopping file watcher.")
      self ! PoisonPill
  }
}
