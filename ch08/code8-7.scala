// 코드 8-7 타임아웃 구현

case class TimeoutMessage(msg:PhotoMessage)

def receive = {
  case rcvMsg: PhotoMessage => {
    messages.find(_.id == rcvMsg.id) match {
      case Some(alreadyRcvMsg) => {
        val newCombinedMsg = new PhotoMessage(
          rcvMsg.id,
          rcvMsg.photo,
          rcvMsg.creationTime.orElse(alreadyRcvMsg.creationTime),
          rcvMsg.speed.orElse(alreadyRcvMsg.speed) )
        pipe ! new CombinedMsg
        // 메시지 정리
        messages -= alreadyRcvMsg
      }
      case None => {
        messages += rcvMsg
        context.system.scheduler.scheduleOnce(
          timeout,
          self,
          new TimeoutMessage(rcvMsg))
      }
    }
  }
  case TimeoutMessage(rcvMsg) => {
    messages.find(_.id == rcvMsg.id) match {
      case Some(alreadyRcvMsg) => {
        pipe ! alreadyRcvMsg
        messages -= alreadyRcvMsg
      }

      case None => // 이미 메시지를 처리했음
    }
  }
}
