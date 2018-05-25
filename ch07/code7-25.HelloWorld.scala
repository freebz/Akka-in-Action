// 코드 7-25 HelloWorld 액터

class HelloWorld extends Actor with ActorLogging {
  def receive = {
    case msg: String =>
      val hello = "Hello %s".format(msg)
      sender() ! hello
      log.info("Sent response {}",hello)
  }
}
