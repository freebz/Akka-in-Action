// 코드 11-7 Publisher 액터의 구현

class Publisher(totalNrBooks: Int, nrBooksPerRequest: Int)
    extends Actor {

  var nrLeft = totalNrBooks
  def receive = {
    case PublisherRequest => {
      if (nrLeft == 0)
        sender() ! BookSupplySoldOut
      else {
        val supply = min(nrBooksPerRequest, nrLeft)
        nrLeft -= supply
        sender() ! new BookSupply(supply)
      }
    }
  }
}
