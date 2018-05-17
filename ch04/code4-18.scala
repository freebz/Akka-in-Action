// 코드 4-18 성급한 데이터베이스 슈퍼바이저 전략

override def supervisorStrategy = OneForOneStrategy(
  maxNrOfRetries = 5,
  withinTimeRange = 60 seconds) {
    case _: DbBrokenConnectionException => Restart
  }
