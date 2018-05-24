// 코드 6-1 분산 GoTicks를 위해 빌드 파일에서 추가해야 하는 부분

"com.typesafe.akka" %% "akka-remote" % akkaVersion,
"com.typesafe.akka" %% "akka-multi-node-testkit" % akkaVersion % "test",
