// 코드 16-2 MessageQueue 트레이트를 확장하고 의미를 혼합해 넣기

class MonitorQueue(val system: ActorSystem)
    extends MessageQueue
    with UnboundedMessageQueueSemantics
    with LoggerMessageQueueSemantics {
  private final val queue = new ConcurrentLinkedQueue[MonitorEnvelope]()
