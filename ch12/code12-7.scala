// 코드 12-7 액티브MQ를 사용하는 경우 테스트하기

val camelUri = "activemq:queue:xmlTest"
val consumer = system.actorOf(
  Props(new OrderConsumerXml(camelUri, probe.ref)))

val activated = CamelExtension(system).activationFutureFor(
  consumer)(timeout = 10 seconds, executor = system.dispatcher)
Await.ready(activated, 5 seconds)

val msg = new Order("me", "Akka in action", 10)
val xml = <order>
            <customerId>{ msg.customerId }</customerId>
            <productId>{ msg.productId }</productId>
            <number>{ msg.number }</number>
          </order>

sendMQMessage(xml.toString())
probe.expectMsg(msg)

system.stop(consumer)
