// 코드 13-19 LogsApi

class LogsApi(
  val logsDir: Path,
  val maxLine: Int
)(
  implicit val executionContext: ExecutionContext,
  val meterializer: ActorMeterializer
) extends EventMarshalling {
  def logFile(id: String) = logsDir.resolve(id)
// 이 뒤에 루트 관련 로직이 온다.
