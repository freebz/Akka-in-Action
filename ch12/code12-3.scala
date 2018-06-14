// 코드 12-3 주문 처리 소비자와 TCP 연결 테스트

val probe = TestProbe()
val camelUri =
  "mina:tcp://localhost:8888?textline=true&sync=false"
val consumer = system.actorOf(
  Props(new OrderConsumerXml(camelUri, probe.ref)))
val activated = CamelExtension(system).activationFutureFor(
  consumer)(timeout = 10 seconds, executor = system.dispatcher)
Await.ready(activated, 5 seconds)

val msg = new Order("me", "Akka in Action", 10)
val xml = <order>
            <customerId>{ msg.customerId }</customerId>
            <productId>{ msg.productId }</productId>
            <number>{ msg.number }</number>
          </order>

val xmlStr = xml.toString().replace("\n", "")
val sock = new Socket("localhost", 8888)
val outputWriter = new PrintWriter(sock.getOutputStream, true)
outputWriter.println(xmlStr)
outputWriter.flush()

probe.expectMsg(msg)

outputWriter.close()
system.stop(consumer)
