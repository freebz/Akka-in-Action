// 코드 13-15 그래프 감독하기

val graphDecider : Supervision.Decider = {
  case _: LogParseException => Supervision.Resume
  case _                    => Supervision.Stop
}

import akka.stream.ActorMaterializerSettings
implicit val materializer = ActorMaterializer(
  ActorMaterializerSettings(system)
    .withSupervisionStrategy(graphDecider)
)
