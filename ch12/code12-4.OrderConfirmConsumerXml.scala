// 코드 12-4 확인 응답을 보내는 소비자

class OrderConfirmConsumerXml(uri: String, next: ActorRef)
    extends Consumer {

  def endpointUri = uri

  def receive = {
    case msg: CamelMessage => {
      try {
        val content = msg.bodyAs[String]
        val xml = XML.loadString(content)
        val order = xml \ "order"
        val customer = (order \ "customerId").text
        val productId = (order \ "productId").text
        val number = (order \ "number").text.toInt
        next ! new Order(customer, productId, number)
        sender() ! "<confirm>OK</confirm>"
      } catch {
        case ex: Exception =>
          sender() ! "<confirm>%s</confirm".format(ex.getMessage)
      }
    }
  }
}
