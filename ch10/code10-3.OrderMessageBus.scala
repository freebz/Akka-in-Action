// 코드 10-3 OrderMessageBus의 완전한 구현

import akka.event.ActorEventBus
import akka.event.{ LookupClassification, EventBus }

class OrderMessageBus extends EventBus
    with LookupClassification
    with ActorEventBus {

  type Event = Order
  type classifier = Boolean
  def mapSize = 2

  protected def classify(event: OrderMessageBus#Event) = {
    event.number > 1
  }

  protected def publish(event: OrderMessageBus#Event,
                        subscriber: OrderMessageBus#Subscriber): Unit = {
    subscriber ! event
  }
}
