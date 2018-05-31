// 코드 9-12 두 메시지를 하나로 합치기

trait GatherMessage {
  val id:String
  val values:Seq[String]
}

case class GatherMessageNormalImpl(id:String, values:Seq[String])
    extends GatherMessage

class SimpleGather(nextStep: ActorRef) extends Actor {
  var messages = Map[String, GatherMessage]()
  def receive = {
    case msg: GatherMessage => {
      messages.get(msg.id) match {
        case Some(previous) => {
          // 메시지 합치기
          nextStep ! GatherMessageNormalImpl(
                       msg.id,
                       previous.values ++ msg.values)
          messages -= msg.id
        }
        case None => messages += msg.id -> msg
      }
    }
  }
}
