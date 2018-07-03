// 코드 16-4 MessageQueue 트레이트의 dequeue 메서드 구현하기

def dequeue(): Envelope = {
  val monitor = queue.poll()
  if (monitor != null) {
    monitor.handle.message match {
      case stat: MailboxStatistics => //skip message
      case _ => {
        stat = MailboxStatistics(
          queueSize = monitor.queueSize,
          receiver = monitor.receiver,
          sender = monitor.handle.sender.toString(),
          entryTime = monitor.entryTime,
          exitTime = System.currentTimeMillis())
        system.eventStream.publish(stat)
      }
    }
    monitor.handle
  } else {
    null
  }
}
