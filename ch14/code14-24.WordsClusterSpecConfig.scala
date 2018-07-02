// 코드 14-24 NultiNode 설정

import akka.remote.testkit.MultiNodeConfig
import com.typesafe.config.ConfigFactory

object WordsClusterSpecConfig extends MultiNodeConfig {
  val seed = role("seed")
  val master = role("master")
  val worker1 = role("worker-1")
  val worker2 = role("worker-2")

  commonConfig(ConfigFactory.parseString("""
    akka.actor.provider="akka.cluster.ClusterActorRefProvider"
    # 테스트에 sigar를 사용하면 안 된다. 경로에 네이티브 라이브러리가 들어있지 않다
    akka.cluster.metrics.collector-class = akka.cluster.JmxMetricsCollector
                                         """))
}
