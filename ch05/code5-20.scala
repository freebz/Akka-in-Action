// 코드 5-20 for 내장을 사용해 경로와 대중교통 정보 조합하기

for(
  (route, advice) <- futureRoute.zip(futurePublicTransport);
  travelAdvice = TravelAdvice(route, advice)
) yield info.copy(travelAdvice = Some(travelAdvice))
