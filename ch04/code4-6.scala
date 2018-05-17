// 코드 4-6 생명주기 훅 호출 테스트

val testActorRef = system.actorOf(
  Props[LifeCycleHooks], "LifeCycleHooks")
testActorRef ! "request"
testActorRef.tell("msg", testActor)
expectMsg("msg")
system.stop(testActorRef)
Thread.sleep(1000)
