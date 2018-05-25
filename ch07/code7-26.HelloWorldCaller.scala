// 코드 7-26 HelloWorldCaller

class HelloWorldCaller(timer: FiniteDuration, actor: ActorRef)
    extends Actor with ActorLogging {

  case class TimeTick(msg: String)

  override def preStart() {
    super.preStart()
    implicit val ec = context.dispatcher
    context.system.scheduler.schedule(
      timer,
      timer,
      self,
      new TimerTick("everybody"))
  }

  def receive = {
    case msg: String => log.info("received {}",msg)
    case tick: TimerTick => actor ! tick.msg
  }
}
