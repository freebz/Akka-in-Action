// 코드 17-4 TypedBasket

package aia.next

import akka.typed._
import akka.typed.ScalaDSL._

object TypedBasket {
  sealed trait Command {
    def shopperId: Long
  }

  final case class GetItems(shopperId: Long,
                            replyTo: ActorRef[Items]) extends Command
  final case class Add(item: Item, shopperId: Long) extends Command

  // Items와 Item을 단순화한 버전
  case class Items(list: Vector[Item] = Vector.empty[Item])
  case class Item(productId: String, number: Int, unitPrice: BigDecimal)

  val basketBehavior =
  ContextAware[Command] { ctx =>
    var items = Items()

    Static {
      case GetItems(productId, replyTo) =>
        replyTo ! items
      case Add(item, productId) =>
        items = Items(items.list :+ item)
      // case GetItems =>
    }
  }
}
