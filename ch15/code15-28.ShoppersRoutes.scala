// 코드 15-28 ShoppersRoutes

trait ShoppersRoutes extends ShopperMarshalling {
  def routes =
    deleteItem ~
    updateItem ~
    getBasket ~
    updateBasket ~
    deleteBasket ~
    pay

  def shoppers: ActorRef

  implicit def requestTimeout: Timeout
  implicit def executionContext: ExecutionContext

  def pay = {
    post {
      pathPrefix("shopper" / ShopperIdSegment / "pay") { shopperId =>
        shoppers ! Shopper.PayBasket(shopperId)
        complete(OK)
      }
    }
  }
