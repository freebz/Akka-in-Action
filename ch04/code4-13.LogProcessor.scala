// 코드 4-13 LogProcessor 동반 객체

object LogProcessor {
  def props(databaseUrls: Vector[String]) =
    Props(new LogProcessor(databaseUrls))
  def name = s"log_processor_${UUID.randomUUID.toString}"
  // 새로운 로그 파일을 표현함
  case class LogFile(file: File)
}
