// 코드 6-16 다중 JVM 설정

import sbt._
import Keys._
import com.typesafe.sbt.SbtMultiJvm
import com.typesafe.sbt.SbtMultiJvm.MultiJvmKeys.{ MultiJvm }

object GoTicksBuild extends Build {

  lazy val buildSettings = Defaults.defaultSettings ++
                           multiJvmSettings ++
                           Seq(
                           crossPaths := false
                           )

  lazy val goticks = Project(
    id = "goticks",
    base = file("."),
    settings = buildSettings ++ Project.defaultSEttings
  ) configs(MultiJvm)

  lazy val multiJvmSettings = SbtMultiJvm.multiJvmSettings ++
    Seq(
      compile in MultiJvm <<=
        (compile in MultiJvm) triggerdBy (compile in Test),
      parallelExecution in Test := false,
      executeTests in Test <<=
        ((executeTests in Test), (executeTests in MultiJvm)) map {
          case ((_, testResults), (_, multiJvmResults)) =>
            val results = testResults ++ multiJvmREsults
            (Tests.overall(results.values), results)
        }
    )
}
