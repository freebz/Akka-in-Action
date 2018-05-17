// 코드 4-15 LogProcessor는 DbWriter를 감독 및 감시함

class LogProcessor(databaseUrls: Vector[String])
    extends Actor with ActorLoggin with LogParsing {
  require(databaseUrls.nonEmpty)

  val initialDatabaseUrl = databaseUrls.head
  var alternateDatabases = databaseUrls.tail

  override def supervisorStrategy = OneForOneStrategy() {
    case _: DbBrokenConnectionException => Restart
    case _: DbNodeDownException => Stop
  }

  var dbWriter = context.actorOf(
    DbWriter.props(initialDatabaseUrl),
    DbWriter.name(initialDatabaseUrl)
  )
  context.watch(dbWriter)

  import LogProcessor._

  def receive = {
    case LogFile(file) =>
      val lines: Vector[DbWriter.Line] = parse(file)
      lines.foreach(dbWriter ! _)
    case Terminated(_) =>
      if(alternateDatabases.nonEmpty) {
        val newDatabaseUrl = alternateDatabases.head
        alternateDatabases = alternateDatabases.tail
        dbWriter = context.actorOf(
          DbWriter.props(newDatabaseUrl),
          DbWriter.name(newDatabaseUrl)
        )
        context.watch(dbWriter)
      } else {
        log.error("All Db nodes broken, stopping.")
        self ! PoisonPill
      }
  }
}
