// 코드 16-3 MessageQueue 트레이트의 enqueue 메서드 구현하기

def enqueue(receiver: ActorRef, handle: Envelope): Unit = {
  val env = MonitorEnvelope(queueSize = queue.sizeIO + 1,
    receiver = receiver.toString(),
    entryTime = System.currentTimeMillis(),
    handle = handle)
  queue add env
}
