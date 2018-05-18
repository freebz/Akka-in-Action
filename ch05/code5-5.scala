// 코드 5-5 Future[Route] 결과를 내놓는 getRoute 메서드

val request = EventRequest(ticketNr)

val futureRoute: Future[Route] = Future {
  callEventService(request).event
}.map { event =>
  val trafficRequest = TrafficRequest(
    destination = event.location,
    arrivalTime = event.time
  )
  callTrafficService(trafficRequest).route
}
