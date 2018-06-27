// 코드 13-14 LogParseException 발생 시 흐름을 계속 유지하기

import akka.stream.ActorAttributes
import akka.stream.Supervision
import LogStreamProcessor.LogParseException

val decider : Supervision.Decider = {
  case _: LogParseException => Supervision.Resume
  case _ => Supervision.Stop
}

val parse: Flow[String, Event, NotUsed] =
  Flow[String].map(LogStreamProcessor.parseLineEx)
    .collect { case Some(e) => e }
    .withAttributes(ActorAttributes.supervisionStrategy(decider))
