// 코드 15-19 persistence-query의 의존 관계

libraryDependencies ++= {
  val akkaVersion     = "2.5.0"
  Seq(
    // 다른 의존 관계 생략
    "com.typesafe.akka" %% "akka-persistence-query-experimental" % akkaVersion,
    // 다른 의존 관계 생략
  )
