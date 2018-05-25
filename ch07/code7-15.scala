// 코드 7-15 대비책과 설정 끌어올림을 함께 사용하는 예제

val configuration = ConfigFactory.load("combined")
val subApplACfg = configuration.getConfig("subApplA")

val config = subApplACfg.withFallback(configuration)
