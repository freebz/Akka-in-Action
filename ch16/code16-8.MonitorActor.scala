// 코드 16-8 액터의 receive 메서드 추적하기

trait MonitorActor extends Actor {

  abstract override def receive = {
    case m: Any => {
      val start = System.currentTimeMillis()
      super.receive(m)
      val end = System.currentTimeMillis()

      val stat = ActorStatistics(
        self.toString(),
        sender.toString(),
        start,
        end)
      context.system.eventStream.publish(stat)
    }
  }
}
