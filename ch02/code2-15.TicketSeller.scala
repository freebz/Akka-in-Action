// 코드 2-15 TicketSeller 메시지

case class Add(tickets: Vector[Ticket])
case class Buy(tickets: Int)
case class Ticket(id: Int)
case class Tickets(event: String,
                   entries: Vector[Ticket] = Vector.empty[Ticket])
case object GetEvent
case object Cancel
