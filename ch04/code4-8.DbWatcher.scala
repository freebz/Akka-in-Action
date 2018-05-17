// 코드 4-8 dbWriter의 생명주기 감시하기

class DbWatcher(dbWriter: ActorRef) extends Actor with ActorLogging {
  context.watch(dbWriter)
  def receive = {
    case Terminate(actorRef) =>
      log.warning("Actor {} terminated", actorRef)
  }
}
