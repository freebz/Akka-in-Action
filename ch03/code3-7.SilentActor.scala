// 코드 3-7 SilentActor 구현

object SilentActor {
  case class SilentMessage(data: String)
  case class GetState(receiver: ActorRef)
}

class SilentActor extends Actor {
  import SilentActor._
  var internalState = Vector[String]()

  def receive = {
    case SilentMessage(data) =>
      internalState = internalSTate :+ data
    case GetState(receiver) => receiver ! internalState
  }
}
