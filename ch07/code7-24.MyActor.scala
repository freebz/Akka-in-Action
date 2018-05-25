// 코드 7-24 LoggingReceive 사용하기

class MyActor extends Actor with ActorLogging {
  def receive = LoggingReceive {
    case ... => ...
  }
}
