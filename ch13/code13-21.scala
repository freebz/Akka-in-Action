// 코드 13-21 POST 처리하기

def postRoute =
  pathPrefix("logs" / Segment) { logId =>
    pathEndOrSingleSlash {
      post {
        entity(as[HttpEntity]) { entity =>
          onComplete(
            entity
              .dataBytes
              .via(logToJsonFlow)
              .toMat(logFileSink(logId))(Keep.right)
              .run()
          ) {
            case Success(IOResult(count, Success(Done))) =>
              complete((StatusCodes.OK, LogReceipt(logId, count)))
            case Success(IOResult(count, Failure(e))) =>
              complete((
                StatusCodes.BadRequest,
                ParseError(logId, e.getMessage)
              ))
            case Failure(e) =>
              complete((
                StatusCodes.BadRequest,
                ParseError(logId, e.getMessage)
              ))
          }
        }
      }
    }
  }
