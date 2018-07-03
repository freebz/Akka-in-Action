// 코드 15-15 장바구니 updateState

private val updateState: (Event => Unit) = {
  case Added(item)              => items = items.add(item)
  case ItemRemoved(id)          => items = items.removeItem(id)
  case ItemUpdated(id, number)  => items = items.updateItem(id, number)
  case Replaced(newItems)       => items = newItems
  case Cleared(clearedItems)    => items = items.clear
}
