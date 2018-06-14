// 코드 12-8 간단한 생산자 테스트하기

implicit val ExecutionContext = system.dispatcher
val probe = TestProbe()
val camelUri = "mina:tcp://localhost:8885?textline=true"
val consumer = system.actorOf(
  Props(new OrderConfirmConsumerXml(camelUri, probe.ref)))

val producer = system.actorOf(
  Props(new SimpleProducer(camelUri)))
val activatedCons = CamelExtension(system).activationFutureFor(
  consumer)(timeout = 10 seconds, executor = system.dispatcher)
val activatedProd = CamelExtension(system).activationFutureFor(
  producer)(timeout = 10 seconds, executor = system.dispatcher)
val camel = Future.sequence(List(activatedCons, activatedProd))
Await.result(camel, 5 seconds)
