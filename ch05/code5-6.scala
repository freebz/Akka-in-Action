// 코드 5-6 리팩토링 버전

val futureRoute: Future[Route] = getEvent(ticketNr).flatMap { event =>
  getRoute(event)
}
