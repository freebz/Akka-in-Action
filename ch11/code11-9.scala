// 코드 11-9 타이머가 있는 재고 시스템 테스트하기

val publisher = TestProbe()
val inventory = system.actorOf(
  Props(new InventoryWithTimer(publisher.ref)))
val stateProbe = TestProbe()
val replyProbe = TestProbe()

inventory ! new SubscribeTransitionCallBack(stateProbe.ref)
stateProbe.expectMsg(
  new CurrentState(inventory, WaitForRequests))

// 테스트를 시작한다.
inventory ! new BookRequest("context1", replayProbe.ref)
stateProbe.expectMsg(
  new Transition(inventory, WaitForRequests, WaitForPublisher))
publisher.expectMsg(PublisherRequest)
  stateProbe.expectMsg(6 seconds,
new Transition(inventory, WaitForPublisher, WaitForRequests))
  stateProbe(expectMsg(
new Transition(inventory, WaitForRequests, WaitForPublisher))
