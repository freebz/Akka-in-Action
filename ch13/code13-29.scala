// 코드 13-29 별도의 로그 싱크에 브로드캐스팅하기

import akka.stream.{ FlowShape, Graph }
import akka.stream.scaladsl.{ Broadcast, GraphDSL, RunnableGraph }

type FlowLike = Graph[FlowShape[Event, ByteString], NotUsed]

def processStates(logId: String): FlowLike = {
  val jsFlow = LogJson.jsonOutFlow
  Flow.fromGraph(
    GraphDSL.create() { implicit builder =>

      import GraphDSL.Implicits._
      // 모든 로그, OK, 경고, 오류, 심각한 오류
      // 다섯 종류의 출력이 필요하다.
      val bcast = builder.add(Broadcast[Event](5))
      val js = builder.add(jsFlow)

      val ok = Flow[Event].filter(_.state == Ok)
      val warning = Flow[Event].filter(_.state == Warning)
      val error = Flow[Event].filter(_.state == Error)
      val critical = Flow[Event].filter(_.state == Critical)

      bcast ~> js.in
      bcast ~> ok       ~> jsFlow ~> logFileSink(logId, Ok)
      bcast ~> warning  ~> jsFlow ~> logFileSink(logId, Warning)
      bcast ~> error    ~> jsFlow ~> logFileSink(logId, Error)
      bcast ~> critical ~> jsFlow ~> logFileSink(logId, Critical)

      FlowShape(bcast.in, js.out)
    })
}

def logFileSource(logId: String, state: State) =
  FileIO.fromPath(logStateFile(logId, state))
def logFileSink(logId: String, state: State) =
  FileIO.toPath(logStateFile(logId, state), Set(CREATE, WRITE, APPEND))
def logStateFile(logId: String, state: State) =
  logFile(s"$logId-${State.norm(state)}")
