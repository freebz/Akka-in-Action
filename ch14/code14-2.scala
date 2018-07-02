// 코드 14-2 시드 노드 시작하기

...
scala> :paste
// Entering paste mode (crrl-D to finish)

import akka.actor._

import akka.cluster._

import com.typesafe.config._

val seedConfig = ConfigFactory.load("seed")
val seedSystem = ActorSystem("words", seedConfig)

// Exiting paste mode, now interpreting.
[Remoting] Starting remoting
[Remoting] listening on addresses :
[akka.tcp:/words@127.0.0.1:2551]
...
[Cluster(akka://words)]
Cluster Node [akka.tcp://words@127.0.0.1:2551]
- Started up successfully
Node [akka.tcp://words@127.0.0.1:2551] is JOINING, roles [seed]
[Cluster(akka://words)] Cluster Node [akka.tcp://words@127.0.0.1:2551]
- Leader is moving node [akka.tcp://words@127.0.0.1:2551] to [Up]
