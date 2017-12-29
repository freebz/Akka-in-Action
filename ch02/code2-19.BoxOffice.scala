// 코드 2-19 모든 티켓의 정보 모으기

case GetEvents =>
  import akka.pattern.ask
  import akka.pattern.pipe

  def getEvents = context.children.map { child =>
    self.ask(GetEvent(child.path.name)).mapTo[Option[Event]]
  }
  def convertToEvents(f: Future[Iterable[Option[Event]]]) =
    f.map(_.flatten).map(l => Events(l.toVector))

  pipe(convertToEvents(Future.sequence(getEvents))) to sender()
