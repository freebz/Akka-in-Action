// 코드 10-2 EventBus 인터페이스

package akka.event

trait EventBus {
  type Event
  type Classifier
  type Subscriber

  /**
    * 구독자를 특정 분류자에 등록하려 시도한다.
    * @return 성공하면 true, 실패하면 false(이미 해당 분류자에 가입했거나
    * 다른 이유로 등록이 불가능한 경우 실패한다.)
    */
  def subscribe(subscriber: Subscriber, to: Classifier): Boolean

  /**
    * 구독자를 특정 분류자로부터 해지하려 시도한다.
    * @return 성공하면 true, 실패하면 false(해당 분류자에 가입하지 않았거나
    * 다른 이유로 해지가 불가능한 경우 실패한다.)
    */
  def unsubscribe(subscriber: Subscriber, from: Classifier): Boolean

  /**
    * 구독자가 이미 가입한 모든 분류자로부터 구독을 해지한다.
    */
  def unsubscribe(subscriber: Subscriber): Unit

  /**
    * 지정한 이벤트를 이 버스에 발행한다.
    */
  def publish(event: Event): Unit
}
