// 코드 13-22 GET 처리하기

def getRoute =
  pathPrefix("logs" / Segment) { logId =>
    pathEndOrSingleSlash {
      get {
        if(Files.exists(logFile(logId))) {
          var src = logFileSource(logId)
          complete(
            HttpEntity(ContentTypes.`application/json`, src)
          )
        } else {
          complete(StatusCodes.NotFound)
        }
      }
    }
  }
