// 코드 13-27 콘텐트 협상을 위해 마살러 제공하기

import akka.http.scaladsl.marshalling.Marshaller
import akka.http.scaladsl.marshalling.ToEntityMarshaller

object LogEnitityMarshaller[Source[ByteString, _]]
def create(maxJsonObject: Int): LEM = {
  val js = ContentTypes.`application/json`
  val txt = ContentTypes.`text/plain(UTF-8)`

  val jsMarshaller = Marshaller.withFixedContentType(js) {
    src:Source[ByteString, _] =>
    HttpEntity(js, src)
  }

  val txtMarshaller = Marshaller.withFixedContentType(txt) {
    src:Source[ByteString, _] =>
    HttpEntity(txt, toText(src, maxMarshaller))
  }

  def toText(src: Source[ByteString, _],
    maxJsonObject: Int): Source[ByteString, _] = {
    src.via(LogJson.jsonToLogFlow(maxJsonObject))
  }
}
