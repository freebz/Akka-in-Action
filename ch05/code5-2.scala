// 코드 5-2 비동기 호출

val request = EventRequest(ticketNr)

val futureEvent: Future[Event] = Future {
  val response = callEventService(request)
  response.event
}
...
