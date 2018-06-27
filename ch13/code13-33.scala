// 코드 13-33 mergeSources 메서드

import akka.stream.scaladsl.Merge

def mergeSources[E](
  sources: Vector[Source[E, _]]
): Option[Source[E, _]] = {
  if(sources.size ==0) None
  else if(sources.size == 1) Some(sources(0))
  else {
    Some(Source.combine(
      sources(0),
      sources(1),
      sources.drop(2) : _*
    )(Merge(_)))
  }
}
