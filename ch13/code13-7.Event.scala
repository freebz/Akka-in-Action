// 코드 13-7 Event 테스트 클래스

case class Event(
  host: String,
  service: String,
  state: State,
  time: ZonedDateTime,
  description: String,
  tag: Option[String] = None,
  metric: Option[Double] = None
)
