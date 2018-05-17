// 코드 4-14 DbWriter 액터

object DbWriter {
  def props(databaseUrl: String) =
    Props(new DbWriter(databaseUrl))
  def name(databaseUrl: String) =
    s"""db-writer-${databaseUrl.split("/").last}"""

  case class Line(time: Long, message: String, messageType: String)
}

class DbWriter(databaseUrl: String) extends Actor {
  val connection = new DbCon(databaseUrl)

  import DbWriter._
  def receive = {
    case Line(time, message, messageType) =>
      connection.write(Map('time -> time,
        'message -> message,
        'messageType -> messageType))
  }

  override def postStop(): Unit = {
    connection.close()
  }
}
