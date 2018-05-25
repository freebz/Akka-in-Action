// 코드 7-31 build.sbt

name := "deploy"

version := "0.1-SNAPSHOT"

organization := "manning"

scalaVersion := "2.12.2"

enablePlugins(JavaAppPackaging)

scriptClasspath +="../conf"

libraryDependencies ++= {
  val akkaVersion = "2.5.0"
  Seq(
    "com.typesafe.akka" %% "akka-actor"     % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j"     % akkaVersion,
    "ch.qos.logback"    % "logback-classic" % "1.2.3",
    "com.typesafe.akka" %% "akka-testkit"   % akkaVersion   % "test",
    "org.scalatest"     %% "scalatest"      % "3.0.1"       % "test"
  )
}
