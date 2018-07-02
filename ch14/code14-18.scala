// 코드 14-18 JobMaster가 작업자를 작업에 참여시키고 Task를 JobWorker에게 보냄

// JobMaster 본문..

import SupervisorStrategy._
override def supervisorStrategy: SupervisorStrategy = stoppingStrategy

def working(jobName:String,
            receptionist:ActorRef,
            cancellable:Cancellable): Receive = {
  case Enlist(worker) =>
    watch(worker)
    workers = workers + worker

  case NextTask =>
    if(textParts.isEmpty) {
      sender() ! WorkLoadDepleted
    } else {
      sender() ! Task(textParts.head, self)
      workGiven = workGiven + 1
      textParts = textParts.tail
    }

  case ReceiveTimeout =>
    if(workers.isEmpty) {
      log.info(s"No workers responded in time. Cancelling $jobName.")
      stop(self)
    } else setReceiveTimeout(Duration.Undefined)

  case Terminated(worker) =>
    log.info(s"Worker $worker got terminated. Cancelling $jobName.")
    stop(self)

// 나머지 코드
