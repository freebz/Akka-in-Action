// 코드 15-25 사용자 정의 Basket 이벤트 직렬화기

import scala.util.Try
import akka.serialization._
import spray.json._

class BasketEventSerializer extends Serializer {
  import JsonFormats._

  val includeManifest: Boolean = false
  val identifier = 123678213

  def toBinary(obj: AnyRef): Array[Byte] = {
    obj match {
      case e: Basket.Event =>
        BasketEventFormat.write(e).compactPrint.getBytes
      case msg =>
        throw new Exception(s"Cannot serialize $msg with ${this.getClass}")
    }
  }

  def fromBinary(bytes: Array[Byte],
                 clazz: Option[Class[_]]): AnyRef = {
    val jsonAst = new String(bytes).parseJson
    BasketEventFormat.read(jsonAst)
  }
}
