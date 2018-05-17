// 코드 4-11 무조건 종료시키는 슈퍼바이저 전략

final val stoppingStragegy: SupervisorStrategy = {
  def stoppingDecider: Decider = {
    case _: Exception => Stop
  }
  OneForOneStrategy()(stoppingDecider)
}
