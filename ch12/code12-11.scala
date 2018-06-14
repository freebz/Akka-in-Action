// 코드 12-11 응답을 사용하는 생산자 테스트하기

implicit val ExecutionContext = system.dispatcher
val probe = TestProbe()
val camelUri = "mina:tcp://localhost:9889?textline=true"
val consumer = system.actorOf(
  Props(new OrderConfirmConsumerXml(camelUri, probe.ref)))

val producer = system.actorOf(
  Props(new OrderConfirmProducerXml(camelUri)))

val activatedCons = CamelExtension(system).activationFutureFor(
  consumer)(timeout = 10 seconds, executor = system.dispatcher)
val activatedProd = CamelExtension(system).activationFutureFor(
  producer)(timeout = 10 seconds, executor = system.dispatcher)

val camel = Future.sequence(List(activatedCons, activatedProd))
Await.result(camel, 5 seconds)
val probeSend = TestProbe()
val msg = new Order("me", "Akka in Action", 10)
probeSend.send(producer, msg)
probe.expectMsg(msg)
probeSend.expectMsg("OK")

system.stop(producer)
system.stop(consumer)
