// 코드 12-9 생산자에서 메시지 변환하기

class OrderProducerXml(uri: String) extends Producer {
  def endpointUri = uri
  override def oneway: Boolean = true

  override protected def transformOutgoingMessage(message: Any): Any =
    message match {
      case msg: Order => {
        val xml = <order>
                    <customerId>{ msg.customerId }</customerId>
                    <productId>{ msg.productId }</productId>
                    <number>{ msg.number }</number>
                  </order>
        xml.toString().replace("\n", "")
      }
      case other => message
    }
}
