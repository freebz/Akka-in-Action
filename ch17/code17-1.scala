// 코드 17-1 Basket 액터 예제

object Basket {
  // .. 다른 메시지들 ..
  case class GetItems(shopperId: Long)
  // .. 다른 메시지들 ..
}

class Basket extends PersistentActor {
  def receiveCommand = {
    // .. 다른 명령들 ..
    case GetItems(shopperId) =>
      // .. 다른 명령들 ..
  }
}

//.. 코드의 다른 부분에서 Basket 액터에게 묻는다 ..
val futureResult = basketActor.ask(GetItems).mapTo(Items)
