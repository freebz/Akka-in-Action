// 코드 13-34 GET /logs에 응답하기

def getLogsRoute =
  pathPrefix("logs") {
    pathEndOrSingleSlash {
      get {
        extractRequest { req =>
          val sources = getFileSources(logsDir).map { src =>
            src.via(LogJson.jsonFramed(maxJsObject))
          }
          mergeSources(sources) match {
            case Some(src) =>
              complete(Marshal(src).toResponseFor(req))
            case None =>
              complete(StatusCodes.NotFound)
          }
        }
      }
    }
  }
