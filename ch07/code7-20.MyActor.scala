// 코드 7-20 다시 보는 로깅 어댑터 만들기

class MyActor extends Actor {
  val log = Logging(context.system, this)
  ...
}
