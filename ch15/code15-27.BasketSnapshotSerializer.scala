// 코드 15-27 사용자 정의 Basket 스냅샷 직렬화기

class BasketSnapshotSerializer extends Serializer {
  import JsonFormats._

  val includeManifest: Boolean = false
  val identifier = 1242134234

  def toBinary(obj: AnyRef): Array[Byte] = {
    obj match {
      case snap: Basket.Snapshot => snap.toJson.compactPrint.getBytes
      case msg => throw new Exception(s"Cannot serialize $msg")
    }
  }

  def fromBinary(bytes: Array[Btye],
    manifest: Option[Class[_]]): AnyRef = {
    jsonStr.parseJson.convertTo[Basket.Snapshot]
  }
}
