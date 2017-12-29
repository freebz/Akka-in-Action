// 코드 2-18 티켓 구매하기

case GetTickets(event, tickets) =>
  def notFound() = sender() ! TicketSeller.Tickets(event)
  def buy(child: ActorRef) =
    child.forward(TicketSeller.Buy(tickets))

  context.child(event).fold(notFound())(buy)
