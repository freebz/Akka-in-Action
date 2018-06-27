// 코드 13-28 GET에서 LogEntityMarshaller 사용하기

implicit val marshaller = LogEntityMarshaller.create(maxJsObject)

def getRoute =
  pathPrefix("logs" / Segment) { logId =>
    pathEndOrSingleSlash {
      get {
        extractRequest { req =>
          if(Files.exists(logFile(logId))) {
            val src = logFileSource(logId)
            complete(Marshal(src).toResponseFor(req))
          } else {
            complete(StatusCodes.NotFound)
          }
        }
      }
    }
  }
