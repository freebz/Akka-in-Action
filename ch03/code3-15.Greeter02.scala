// 코드 3-15 리스터를 활용해 Greeter 액터 테스트를 더 쉽게 만들기

object Greeter02 {
  def props(listener: Option[ActorRef]) = None =
    Props(new Greeter02(listener))
}
class Greeter02(listener: Option[ActorRef]
    extends Actor with ActorLogger {
  def receive = {
    case Greeting(msg) =>
      val message = "Hello" + who + "!"
      log.info(message)
      listener.foreach(_ ! message)
  }
}
