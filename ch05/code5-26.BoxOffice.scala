// 코드 5-26 event 정보 수집하기

class BoxOffice(implicit timeout: Timeout) extends Actor {

  // ... 코드 생략

  case GetEvent(event) =>
    def notFound() = sender() ! None
    def getEvent(child: ActorRef) = child forward TicketSeller.GetEvent
    context.child(event).fold(notFound())(getEvent)

  case GetEvents =>
    import akka.pattern.ask
    import akka.pattern.pipe

    def getEvents: Iterable[Future[Option[Event]]] = context.children.map {
      child =>
      self.ask(GetEvent(child.path.name)).mapTo[Option[Event]]
    }
    def convertToEvents(f: Future[Iterable[Option[Event]]]): Future[Events] =
      f.map(_.flatten).map(l => Events(l.toVector))

    pipe(
      convertToEvents(Future.sequence(getEvents))
    ) to sender()
