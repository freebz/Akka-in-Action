// 코드 10-4 OrderMessageBus 사용하기

val bus = new OrderMessageBus

val singleBooks = TestProbe()
sub.subscribe(singleBooks.ref, false)
val multiBooks = TestProbe()
bus.subscribe(multiBooks.ref, true)

val msg = new Order("me", "Akka in Action", 1)
bus.publish(msg)
singleBooks.expectMsg(msg)
multiBooks.expectNoMsg(3 seconds)

val msg2 = new Order("me", "Akka in Action", 3)
bus.publish(msg2)
singleBooks.expectNoMsg(3 seconds)
multiBooks.expectMsg(msg2)
