// 코드 5-3 event 결과 처리하기

futureEvent.foreach { event =>
  val trafficRequest = TrafficRequest(
    destination = event.location,
    arrivalTime = event.time
  )
  val trafficResponse = callTrafficService(trafficRequest)
  println(trafficResponse.route)
}
