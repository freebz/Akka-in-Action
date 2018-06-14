// 코드 12-10 생산자에서 응답과 메시지 변환하기

class OrderConfirmProducerXml(uri: String) extends Producer {
  def endpointUri = uri
  override def oneway: Boolean = false

  override def transformOutgoingMessage(message: Any): Any =
    message match {
      case msg: Order => {
        val xml = <order>
                    <customerId>{ msg.customerId }</customerId>
                    <productId>{ msg.productId }</productId>
                    <number>{ msg.number }</number>
                  </order>
        xml.toString().replace("\n", "") + "\n"
      }
      case other => message
    }

  override def transformResponse(message: Any): Any =
    message match {
      case msg: CamelMessage => {
        try {
          val content = msg.bodyAs[String]
          val xml = XML.loadString(content)
          val res = (xml \ "confirm").text
          res
        } catch {
          case ex: Exception =>
            "TransformException: %s".format(ex.getMessage)
        }
      }
      case other => message
    }
}
