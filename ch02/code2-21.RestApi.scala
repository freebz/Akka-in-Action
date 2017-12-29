// 코드 2-21 이벤트 루트 정의

def eventRoute =
  pathPrefix("events" / Segment) { event =>
    pathEndOrSingleSlash {
      post {
        // POST /events/:event
        entity(as[EventDescription]) { ed =>
          onSuccess(createEvent(event, ed.tickets)) {
            BoxOffice.eventCreated(event) => complete(Created, event)
            case BoxOffice.EventExists =>
                       val err = Error(s"$event event exists already.")
                       complete(BadRequest, err)
          }
        }
      } ~
      get {
        // GET /events/:event
        onSuccess(getEvent(event)) {
          _.fold(complete(NotFound))(e => complete(OK, e))
        }
      } ~
      delete {
        // DELETE /events/:event
        onSuccess(cancelEvent(event)) {
          _.fold(complete(NotFound))(e => complete(OK, e))
        }
      }
    }
  }
}
