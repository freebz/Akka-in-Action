// 코드 5-4 event 결과를 연쇄시키기

val futureRoute: Future[Route] = futureEvent.map { event =>
  val trafficRequest = TrafficRequest(
    destination = event.location,
    arrivalTime = event.time
  )
  val trafficResponse = callTrafficService(trafficRequest)
  trafficResponse.route
}
