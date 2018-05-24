// 코드 6-3 리모팅 설정

import com.typesafe.config._
import akka.actor._

val config = ConfigFactory.parseString(conf)
val backend = ActorSystem("backend", config)
