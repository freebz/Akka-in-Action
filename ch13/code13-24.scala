// 코드 13-24 POST에서 EventUnmarshaller 사용하기

implicit val unmarshaller = EventUnmarshaller.create(maxLine, maxJsObject)

def postRoute =
  pathPrefix("logs" / Segment) { logId =>
    pathEndOrSingleSlash {
      post {
        entity(as[Source[Event, _]]) { src =>
          onComplete(
            src.via(outFlow)
              .toMat(logFileSink(logId))(Keep.right)
              .run()
          ) {
            // Future의 결과를 처리하는 부분(여기서는 생략함)
            // 지금까지 다뤘던 예제와 같다.
