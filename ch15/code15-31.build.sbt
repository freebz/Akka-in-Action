// 코드 15-31 클러스터 싱글톤고 ㅏ샤딩의 의존 관계

libraryDependencies ++= {
  val akkaVersion = "2.5.0"
  Seq(
    // 다른 의존 관계 생략
    "com.typesafe.akka" %% "akka-cluster-tools" % akkaVersion,
    "com.typesafe.akka" %% "akka-cluster-sharding" % akkaVersion,
    // 다른 의존 관계 생략
  )
