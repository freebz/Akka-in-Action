// 코드 15-17 Basket의 receiveRecover

def receiveRecover = {
  case event: Event =>
    nrEventsRecovered = nrEventsRecovered + 1
    updateState(event)
  case SnapshotOffer(_, snapshot: Basket.Sanpshot) =>
    log.info(s"Recovering baskets from snapshot: $snapshot for $persistenceId")
    items = snapshot.items
}
