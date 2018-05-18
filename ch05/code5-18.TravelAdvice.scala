// 코드 5-18 TravelAdvice 클래스

case class TravelAdvice(
  routeByCar:Option[RouteByCar] = None,
  publicTransportAdvice: Option[PublicTransportAdvice] = None
)
