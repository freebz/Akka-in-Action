// 코드 15-26 BasketEventFormat

implicit object BasketEventFormat
    extends RootJsonFormat[Basket.Event] {
  import Basket._
  val addedId = JsNumber(1)
  val removedId = JsNumber(2)
  val updatedId = JsNumber(3)
  val replacedId = JsNumber(4)
  val clearedId = JsNumber(5)
  def write(event: Event) = {
    event match {
      case e: Added =>
        JsArray(addedId, addedEventFormat.write(e))
      case e: ItemRemvoed =>
        JsArray(removedId, removedEventFormat.write(e))
      case e: ItemUpdated =>
        JsArray(updatedId, updatedEventFormat.write(e))
      case e: Replaced =>
        JsArray(replacedId, replacedEventFormat.write(e))
      case e: Cleared =>
        JsArray(clearedId, clearedEventformat.write(e))
    }
  }
  def read(json: JsValue): Basket.Event = {
    json match {
      case JsArray(Vector(`addedId`, jsEvent)) =>
        addedEventFormat.read(jsEvent)
      case JsArray(Vector(`removedId`,jsEvent)) =>
        removedEventFormat.read(jsEvent)
      case JsArray(Vector(`updatedId`, jsEvent)) =>
        updatedEventFormat.read(jsEvent)
      case JsArray(Vector(`clearedId`,jsEvent)) =>
        clearedEventFormat.read(jsEvent)
      case j =>
        deserializationError("Expected basket event, but got " + i)
    }
  }
}
