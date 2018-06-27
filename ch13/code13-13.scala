// 코드 13-13 한 흐름으로 이벤트 필터링하기

val flow: Flow[ByteString, ByteString, NotUsed] =
  Framing.delimiter(ByteString("\n"), maxLine)
    .map(_.decodeString("UTF8"))
    .map(LogStreamProcessor.parseLineEx)
    .collect { case Some(e) => e }
    .filter(_.state == filterState)
    .map(event => ByteString(event.toJson.compactPrint))
