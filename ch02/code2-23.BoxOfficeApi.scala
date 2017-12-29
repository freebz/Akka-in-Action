// 코드 2-23 티켓 루트 정의

def ticketsRoute =
  pathPrefix("events" / Segment / "ticket") { event =>
    post {
      pathEndOrSingleSlash {
        // POST /events/:event/tickets
        entity(as[TicketRequest]) { request =>
          onSuccess(requestTickets(event, request.tickets)) { tickets =>
            if(tickets.entries.isEmpty) complete(NotFound)
            else complete(Created, tickets)
          }
        }
      }
    }
  }
