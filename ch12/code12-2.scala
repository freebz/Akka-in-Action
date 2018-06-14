// 코드 12-2 주문 처리 소비자 테스트하기

val msg = new Order("me", "Akka in Action", 10)
val xml = <order>
            <customerId>{ msg.customerId }</customerId>
            <productId>{ msg.productId }</productId>
            <number>{ msg.number }</number>
          </order>
val msgFile = new File(dir, "msg1.xml")
FileUtils.write(msgFile, xml.toString())
probe.expectMsg(msg)
system.stop(consumer)
