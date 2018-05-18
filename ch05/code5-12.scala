// 코드 5-12 TicketInfo 케이스 클래스

case class TicketInfo(ticketNr:String,
                      event:Option[Event]=None,
                      route:Option[Route]=None)
