// 코드 14-19 JobWorker가 Task를 처리하고 TaskResult를 돌려보냄

def enlisted(jobName: String, master: ActorRef): Receive = {
  case ReceiveTimeout =>
    master ! NextTask

  case Task(textPart, master) =>
    val countMap = processTask(textPart)
    processed = processed + 1
    master ! TaskResult(countMap)
    master ! NextTask

  case WorkLoadDepeted =>
    log.info(s"Work load ${jobName} is depleted, retiring...")
    setReceiveTimeout(Duration.Undefined)
    become(retired(jobName))

  case Terminated(_) =>
    setReceiveTimeout(Duration.Undefined)
    log.error(s"Master terminated that ran job ${jobName}, stopping self.")
    stop(self)
}

def retired(jobName: String): Receive = {
  case Terminated(_) =>
    log.error(s"Master terminated that ran Job ${jobName}, stopping self.")
    stop(self)
  case _ => log.error("I'm retired.")
} // 이 다음에 processTask에 대한 정의가 온다.
