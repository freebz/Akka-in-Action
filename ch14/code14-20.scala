// 코드 14-20 JobMaster는 중간 결과를 모두 저장하고 취합해서 JOb으로 받았던 작업을 완료함

def working(jobName: String,
            receptionist: ActorRef,
            cancellable: Cancellable): Receive = {

  ...

  case TaskResult(countMap) =>
  intermediateResult = intermediateResult :+ countMap
  workReceived = workReceived + 1

  if(textParts.isEmpty && workGiven == workReceived) {
    cancellable.cancel()
    become(finishing(jobName, receptionist, workers))
    setReceiveTimeout(Duration.Undefined)
    self ! MergeResults
  }
}
...
def finishing(jobName: String,
              receptionist: ActorRef,
              workers: Set{ActorRef]): Receive = {
  case MergeResults =>
    val mergedMap = merge()
    workers.foreach(stop(_))
    receptionist ! WordCount(jobName, mergedMap)

  case Terminated(worker) =>
    log.info(s"Job $jobName is finishing. Worker ${worker.path.name} is stopped.")
}
...
