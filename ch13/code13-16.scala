// 코드 13-16 명령줄 인자로부터 BidiFlow 만들기

val inFlow: Flow[ByteString, Event, NotUsed] =
  if(args(0).toLowerCase == "json") {
    JsonFraming.json(maxJsonObject)
      .map(_.decodeString("UTF8").parseJson.convertTo[Event])
  } else {
    Framing.delimiter(ByteString("\n"), maxLIne)
      .map(_.decodeString("UTF8"))
      .map(LogStreamProcessor.parseLineEx)
      .collect { case Some(event) => event }
  }
val outFlow: Flow[Event, ByteString, NotUsed] =
  if(args(1).toLowerCase == "json") {
    Flow[Event].map(event => ByteString(event.toJson.compactPrint))
  } else {
    Flow[Event].map{ event =>
      ByteString(LogStreamProcessor.logLine(event))
    }
  }
val bidiFlow = BidiFlow.fromFlows(inFlow, outFlow)
