// 코드 15-24 아카 Serializer 트레이트

trait Serializer {
  /**
    * 직렬화기의 구현을 식별하기 위한 완전히 유일한 값이다.
    * 네트워크 트래픽을 최적화하는 데 사용된다.
    * 0부터 16까지의 값은 아카 내부에서 사용하게 예약되어 있다.
    */
  def identifier: Int

  /**
    * 주어진 객체를 Byte의 Array로 직렬화한다.
    */
  def toBinary(o: AnyRef): Array[Byte]

  /**
    * 직렬화기가 fromBinary 메서드에서 매니페스트(타입 힌트)를 필요로 하는지를 반환한다.
    */
  def includeManifest: Boolean

  /**
    * Byte의 Array에서 객체를 만들어낸다. 이때 타입 힌트(매니페스트)를 추가로 받을 수 있다.
    * 클래스는 ActorSystem.dynamicAccess를 사용해 적재되어야 한다.
    */
  def fromBinary(bytes: Array[Byte], manifest: Option[class[_]]): AnyRef
}
