// 코드 10-1 EventStream의 동작

val DeliverOrder = TestProbe()
val giftModule = TestProbe()

system.eventStream.subscribe(
  DeliverOrder.ref,
  classOf[Order])
system.eventStream.subscribe(
  giftModule.ref,
  classOf[Order])

val msg = new Order("me", "Akka in Action", 3)
system.eventStream.publish(msg)

DeliverOrder.expectMsg(msg)
giftModule.expectMsg(msg)

system.eventStream.unsubscribe(giftModule.ref)

system.eventStream.publish(msg)
DeliverOrder.expectMsg(msg)
giftModule.expectNoMsg(3 seconds)
