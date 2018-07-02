// 코드 14-5 시드 노드 1이 클러스터에서 이탈함

scala> val address = Cluster(seedSystem).selfaddress

address: akka.actor.Address = akka.tcp://words@127.0.0.1:2551

scala> Cluster(seedSystem).leave(address)

[Cluster(akka://words)] Cluster Node [akka.tcp://words@127.0.0.1:2551]
- Narked address [akka.tcp://words@127.0.0.1:2551] as [Leaving]
[Cluster(akka://words)] Cluster Node [akka.tcp://words@127.0.0.1:2551]
- Leader is moving node [akka.tcp://words@127.0.0.1:2551] to [Exiting]
[Cluster(akka://words)] Cluster Node [akka.tcp://words@127.0.0.1:2551]
- Shutting down...
[Cluster(akka://words)] Cluster Node [akka.tcp://words@127.0.0.1:2551]
- Successfully shut down
