// 코드 13-32 GET /logs/[log-id]/not-ok에 대한 응답

def getLogNotOkRoute =
  pathPrefix("logs" / Segment /"not-ok") { logId =>
    pathEndOrSingleSlash {
      get {
        extractRequest { req =>
          complete(Marshal(mergeNotOk(logId)).toResponseFor(req))
        }
      }
    }
  }
