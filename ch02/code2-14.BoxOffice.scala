// 코드 2-14 BoxOffice 메시지

case class CreateEvent(name: String, tickets: Int)
case class GetEvent(name: String)
case object GetEvents

case class GetTickts(event: String, tickets: Int)
case class CancelEvent(name: String)

case class Event(name: String, tickets: Int)
case class Events(events: Vector[Event])

sealed trait EventResponse
case class EventCreated(event: Event) extends EventResponse
case object EventExists extends EventResponse
