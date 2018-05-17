// 코드 4-3 preRestart 생명주기 훅

override def preRestart(reason: Throwable, message:
    Option[Any]): Unit = {
  println("preRestart")
  super.preRestart(reason, message)
}
