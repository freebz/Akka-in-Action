// 코드 13-5 실체화한 값 유지하기

import akka.Done
import akka.stream.scaladsl.Keep

val graphLeft: RunnableGraph[Future[IOResult]] =
  source.toMat(sink)(Keep.left)

val graphRight: RunnableGraph[Future[IOResult]] =
  source.toMat(sink)(Keep.right)

val graphBoth: RunnableGraph[(Future[IOResult], Future[IOResult])] =
  source.toMat(sink)(Keep.both)

val graphCustom: RunnableGraph[Future[Done]] =
  source.toMat(sink) { (l, r) =>
    Future.sequence(List(l,r)).map(_ => Done)
}
