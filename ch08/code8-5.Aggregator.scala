// 코드 8-5 Aggregator

class Aggregator(timeout:Duration, pipe:ActorRef) extends Actor {
  val messages = new ListBuffer[PhotoMessage]
  def receive = {
    case rcvMsg: PhotoMessage => {
      messages.find(_.id == rcvMsg.id) match {
        case Some(alreadyRcvMsg) => {
          val newCombinedMsg = new PhotoMessage(
            rcvMsg.id,
            rcvMsg.photo,
            rcvMsg.creationTime.orElse(alreadyRcvMsg.creationTime),
            rcvMsg.speed.orElse(alreadyRcvMsg.speed) )
          pipe ! newCombinedMsg
          // 메시지 정리
          messages -= alreadyRcvMsg
        }
        case None => messages += rcvMsg
      }
    }
  }
}
