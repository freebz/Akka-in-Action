// 코드 3-19 EchoActor

class EchoActor extends Actor {
  def receive = {
    case msg =>
      sender() ! msg
  }
}
