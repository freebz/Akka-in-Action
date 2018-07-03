// 코드 15-1 akka-persistence의 의존 관계

parallelExecution in Test := false

fork := true

libraryDependencies ++= {
  val akkaVersion = "2.5.0"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-persistence" % akkaVersion,
    // 이 장의 나머지 부분에 필요한 의존 관계
  }
}
