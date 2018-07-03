// 코드 15-12 Basket 명령과 이벤트들

sealed trait Command extends Shopper.Command
case class Add(item: Item, shopperId: Long) extends Command
case class RemoveItem(productId: String, shopperId: Long) extends Command
case class UpdateItem(productId: String,
                      number: Int,
                      shopperId: Long) extends Command
case class Clear(shopperId: Long) extends Command
case class Replace(items: Items, shopperId: Long) extends Command
case class GetItems(shopperId: Long) extends Command

case class CountRecoveredEvents(shopperId: Long) extends Command
case class RecoveredEventsCount(count: Long)

sealed trait Event extends Serializable
case class Added(item: Item) extends Event
case class ItemRemoved(productId: String) extends event
case class ItemUpdated(productId: String, number: Int) extends Event
case class Replaced(items: Items) extends Event
case class Cleared(clearedItems: Items) extends Event

case class Snapshot(items: Items)
