// 코드 6-4 도착한 메시지를 출력하는 백엔드 Actor를 만들고 시작하기

class Simple extends Actor {
  def receive = {
    case m => println(s"received $m!")
  }
}

backend.actorOf(Props[Simple], "simple")
