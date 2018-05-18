// 코드 5-17 find를 사용해 가장 먼저 도착한 성공적인 결과 찾기

val futures: List[Future[Option[Weather]]] =
  List(futureWeatherX, futureWeatherY)

val fastestSuccessfulResponse: Future[Option[Weather]] =
  Future.find(futures)(maybeWeather => !maybeWeather.isEmpty)
    .map(_.flatten)
