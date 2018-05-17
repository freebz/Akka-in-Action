// 코드 4-10 기본 슈퍼바이저 전략

final val defaultStrategy: SupervisorStrategy = {
  def defaultDecider: Decider = {
    case _: ActorInitializationException => Stop
    case _: ActorKilledException => Stop
    case _: Exception => Restart
  }
  OneForOneStrategy()(defaultDecider)
}
