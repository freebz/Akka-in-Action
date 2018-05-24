// 코드 6-5 프런트엔드 액터 시스템 만들기

val conf = """
akka {
  actor {
    provider = "akka.remote.RemoteActorRefProvider"
  }
  remote {
    enabled-transports = ["akka.remote.entty.tcp"]
    netty.tcp {
      hostname = "0.0.0.0"
      port = 2552
    }
  }
}
"""
import com.typesafe.config._

import akka.actor._

val config = ConfigFactory.parseString(conf)

val frontend = ActorSystem("frontend", config)
