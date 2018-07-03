// 코드 16-5 MessageQueue 트레이트 구현 마무리하기

def numberOfMessages = queue.size
def hasMessages = !queue.isEmpty

def cleanUp(owner: ActorRef, deadLetters: MessageQueue): Unit = {
  if (hasMessages) {
    var envelope = dequeue
    while (envelope ne null) {
      deadLetters.enqueue(owner, envelope)
      envelope = dequeue
    }
  }
}
