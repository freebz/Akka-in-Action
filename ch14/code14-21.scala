// 코드 14-21 JobMaster 실패 시 JObReceptionist가 JobRequest를 다시 처리함

case Terminated(jobMaster) =>
  jobs.find(_.jobMaster == jobMaster).foreach { failedJob =>
    log.error(s"Job Master $jobMaster terminated before finishing job.")

    val name = failedJob.name
    log.error(s"Job ${name} failed.")
    val nrOfRetries = retries.getOrElse(name, 0)
    if(maxRetries > nrOfRetries) {
      if(nrOfRetries == maxRetries -1) {
        // 최대 재시도 횟수 이후에 작업자가 제대로 동작하는 것을 시뮬레이션한다.

        val text = failedJob.text.filterNot(_.contains("FAIL"))
        self.tell(JobRequest(name, text), failedJob.respondTo)
      } else self.tell(JobRequest(name, failedJob.text), failedJob.respondTo)

      updateRetries
    }
  }
}
