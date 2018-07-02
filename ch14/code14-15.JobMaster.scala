// 코드 14-15 라우터를 사용해 Work 메시지 방송하기

class JobMaster extends Actor
                   with ActorLogging
                   with CreateWorkerRouter {
  // JobMaster 액터의 본문 내부..
  var router = createWorkerRouter

  def receive = idle

  def idle: Receive = {
    case StartJob(jobName, text) =>
      textParts = text.grouped(10).toVector
      val cancel = system.scheduler.schedule(0 millis,
                                             1000 millis,
                                             router,
                                             Work(jobName, self))
      become(working(jobName, sender(), cancel))
  }
  // 나머지 코드
