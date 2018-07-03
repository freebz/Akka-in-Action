// 코드 16-1 우편함 통계를 저장하기 위한 데이터 컨테이너

case class MonitorEnvelope(queueSize: Int,
                           receiver: String,
                           entryTime: Long,
                           handle: Envelope)

case class MailboxStatistics(queueSize: Int,
                             receiver: String,
                             sender: String,
                             entryTime: Long,
                             exitTime: Long)
