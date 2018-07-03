// 코드 16-6 사용자 정의 우편함을 사용하는 MailboxType 구현

class MonitorMailboxType(settings: ActorSystem.Settings, config: Config)
    extends akka.dispatch.MailboxType
    with ProducesMessageQueue[MonitorQueue]{

  final override def create(owner: Option[ActorRef],
                            system: Option[ActorSystem]): MessageQueue = {
    system match {
      case Some(sys) => new MonitorQueue(sys)
        new MonitorQueue(sys)
      case _ => throw new IllegalArgumentException("requires a system")
    }
  }
}
