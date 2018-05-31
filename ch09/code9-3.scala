// 코드 9-3 코드로 BalancingPool 만들기

val router = system.actorOf(
  BalancingPool(5).props(Props(new GetLicense(endProbe.ref))),
  "poolRouter"
)
