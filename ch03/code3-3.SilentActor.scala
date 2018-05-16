// 코드 3-3 SilentActor 유형 구현: 테스트에 실패하는 첫 번째 구현체

class SilentActor extends Actor {
  def receive = {
    case msg =>
  }
}
