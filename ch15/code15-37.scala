// 코드 15-37 AtLeastOnceDeliveryActor에 맞춰 배달 확인을 보내주는 echo 액터

// src/multi-jvm/scala/aia/persistence/enshahar/AtLeastOnceMultiJvmSpec.java의 일부분
val serverEcho = system.actorOf(Props(new Actor {
  def receive = {
    case Msg(deliveryId, pathToOriginal, s) =>
      sender() ! Confirm(deliveryId)
      val orignalSender = system.actorSelection(pathToOrignal)
      orignalSender ! s
    case msg: AnyRef => {
      sender() ! msg
    }
  }
}), "echo")
