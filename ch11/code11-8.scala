// 코드 11-8 천이 이벤트를 얻기 위해 구독하기

val publisher = system.actorOf(Props(new Publisher(2,2)))

val inventory = system.actorOf(Props(new Inventory(publisher)))
val stateProbe = TestProbe()
inventory ! new SubscribeTransitionCallBack(stateProbe.ref)
stateProbe.expectMsg(new CurrentState(inventory, WaitForRequests))
