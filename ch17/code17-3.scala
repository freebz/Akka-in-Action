// 코드 17-3 akka-typed를 사용해 상품 목록 가져오기

"return the items in a typesafe way" in {
  import akka.typed._
  import akka.typed.scaladsl.AskPattern._
  import scala.concurrent.Future
  import scala.concurrent.duration._
  import scala.concurrent.Await

  implicit val timeout = akka.util.Timeout(1 second)

  val macbookPro =
    TypedBasket.Item("Apple Macbook Pro", 1, BigDecimal(2499.99))
  val displays =
    TypedBasket.Item("4K Display", 3, BigDecimal(2499.99))

  val sys: ActorSystem[TypedBasket.Command] =
    ActorSystem("typed-basket", Props(TypedBasket.basketBehavior))

  sys ! TypedBasket.Add(macbookPro, shopperId)
  sys ! TypedBasket.Add(displays, shopperId)

  val items: Future[TypedBasket.Items] =
    sys ? (TypedBasket.GetItems(shopperId, _))

  val res = Await.result(items, 10 seconds)
  res should equal(TypedBasket.ITems(Vector(macbookPro, displays)))
  //sys ? Basket.GetItems
  sys.terminate()
}
