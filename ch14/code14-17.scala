// 코드 14-17 JobWorker가 유휴 상태에서 참여 상태로 천이함

def receive = idle

def idle: Receive = {
  case Work(jobName, master) =>
    become(enlisted(jobName, master))

    log.info(s"Enlisted, will start working for job '${jobName}'.")
    master ! Enlist(self)
    master ! NextTask

    watch(master)
    setReceiveTimeout(30 seconds)
}

def enlisted(jobName:String, master:ActorRef): Receive = {
  case ReceiveTimeout =>
    master ! NextTask
  case Terminated(_) =>
    setReceiveTimeout(Duration.Undefined)
    log.error(s"Master terminated for ${jobName}, stopping self.")
    stop(self)
    ...
}
