// 코드 15-38 12.2.2절에서처럼 echo 액터를 사용해 메시지 처리하기

// AtLeastOnceDeliveryactor의 receiveCommand 메서드를 다음과 같이 바꾼다.
override def receiveCommand: Receive = {
  case s: String           => persist(MsgSent(s,pathToTester))(updateState)
  case Confirm(deliveryId) => persist(MsgConfirmed(deliveryId))(updateState)
  case Msg(deliveryId, pathToOrignal, s) =>
    persist(MsgConfirmed(deliveryId))(updateState)
    val orignalSender = context.actorSelection(pathToOrignal)
    orignalSender ! s
}

// serverEcho 정의를 다음과 같이 바꾼다.
val serverEcho = system.actorOf(Props(new Acotr {
  def receive = {
    case msg: AnyRef => {
      sender() ! msg
    }
  }
}), "echo")
