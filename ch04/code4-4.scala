// 코드 4-4 postRestart 생명주기 훅

override def postRestart(reason: Throwable): Unit = {
  println("postRestart")
  super.postRestart(reason)
}
