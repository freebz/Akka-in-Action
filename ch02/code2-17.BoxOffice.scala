// 코드 2-17 BoxOffice가 TicketSeller를 만듦

def createTicketSeller(name: String) =
  context.actorOf(TicketSeller.props(name), name)

def receive = {
  case CreateEvent(name, tickets) =>
    def create() = {
      val eventTickets = createTicketSeller(name)
      val newTickets = (1 to tickets).map { ticketId =>
        TicketSeller.Ticket(ticketId)
      }.toVector
      eventTickets ! TicketSeller.Add(newTickets)
      sender() ! EventCreated
    }
    context.child(name).fold(create())(_ => sender() ! EventExists)
