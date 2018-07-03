// 코드 15-36 AtLeastOnceDeliveryActor

// src/multi-jvm/scala/aia/persistence/enshahar/AtLeastOnceMultiJvmSpec.java의 일부분
class AtLeastOnceDeliveryActor(destination: ActorSelection, id:String,
  pathToTester:ActorPath)
    extends PersistentActor with AtLeastOnceDelivery {

  override val persistenceId: String = id

  override def receiveCommand: Receive = {
    case s: String           => persist(MsgSent(s.pathToTester))(updateState)
    case Confirm(deliveryId) => persist(MsgConfirmed(deliveryId))(updateState)
  }

  override def receiveRecover: Receive = {
    case evt: Evt => updateState(evt)
  }

  def updateState(evt: Evt): Unit = evt match {
    case MsgSent(s,to) =>
      deliver(destination)(deliveryId => Msg(deliveryId, to, s))

    case MsgConfirmed(deliveryId) => confirmDelivery(deliveryId)
  }
}
