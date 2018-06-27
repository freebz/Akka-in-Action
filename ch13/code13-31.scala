// 코드 13-31 OK가 아닌 모든 상태 병합하기

import akka.stream.SourceShape
import akka.stream.scaladsl.{ GraphDSL, Merge }

def mergeNotOk(logId: String): Source[ByteString, NotUsed] = {
  val warning = logFileSource(logId, Warning)
    .via(LogJson.jsonFramed(maxJsObject))
  val error = logFileSource(logId, Error)
    .via(LogJson.jsonFramed(maxJsObject))
  val critical = logFileSource(logId, Critical)
    .via(LogJson.jsonFramed(maxJsObject))

  Source.fromGraph(
    GraphDSL.create() { implicit builder =>
      import GraphDSL.Implicits._

      val warningShape = builder.add(warning)
      val errorShape = builder.add(error)
      val criticalShape = builder.add(critical)
      var merge = builder.add(Merge[ByteString](3))

      warningShape ~> merge
      errorShape ~> merge
      criticalShape ~> merge
      SourceShape(merge.out)
    })
}
