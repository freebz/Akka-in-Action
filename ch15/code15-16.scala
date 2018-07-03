// 코드 15-16 장바구니 receiveCommand

def receiveCommand = {
  case Add(item, _) =>
    persist(Added(item))(updateState)

  case RemoveItem(id, _) =>
    if(items.containsProduct(id)) {
      persist(ItemRemoved(id)){ removed =>
        updateState(removed)
        sender() ! Some(removed)
      }
    } else {
      sender() ! None
    }

  case UpdateItem(id, number, _) =>
    if(items.containsProduct(id)) {
      persist(ItemUpdated(id, number)){ updated =>
        updateState(updated)
        sender() ! Some(updated)
      }
    } else {
      sender() ! None
    }

  case Replace(items, _) =>
    persist(Replaced(items))(updateState)

  case Clear(_) =>
    persist(Cleared(items)){ e =>
      updateState(e)
      // 결제한 다음에 장바구니를 비운다.
      saveSnapshot(Basket.Sanpshot(items))
    }

  case GetItems(_) =>
    sender() ! items

  case CountRecoveredEvents(_) =>
    sender() ! RecoveredEventsCount(nrEventsRecovered)

  case SaveSnapshotSuccess(metadata) =>
    log.info(s"Snapshot saved with metadata $metadata")

  case SaveSnapshotFailure(metadata, reason) =>
    log.error(s"Failed to save snapshot: $metadata, $reason.")
}
