// 코드 12-5 TCP 연결과 확인 응답을 보내는 소비자 테스트하기

val probe = TestProbe()
val camelUri =
  "mina:tcp://localhost:8887?textline=true"
val consumer = system.actorOf(
  Props(new OrderConfirmConsumerXml(camelUri, probe.ref)))
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
val sock = new Socket("localhost", 8887)
val outputWriter = new PrintWriter(sock.getOutputStream, true)
outputWriter.println(xmlStr)
outputWriter.flush()
val responseReader = new BufferReader(
  new InputStreamReader(sock.getInputStream))
val response = responseReader.readLine()
response must be("<confirm>OK</confirm>)
probe.expectMsg(msg)

responseReader.close()
outputWriter.close()
system.stop(consumer)
