// 코드 14-13 MemberUp 이벤트를 활용해 최소한의 작업자 노드 설정하기

object Main extends App {
  val config = ConfigFactory.load()
  val system = ActorSystem("words", config)

  println(s"Starting node with roles: ${Cluster(system).selfRoles}")
  val roles = system.settings
                    .config
                    .getStringList("akka.cluster.roles")
  if(roles.contains("master")) {
    Cluster(system).registerOnMemberUp {
      val receptionist = system.actorOf(Props[JobReceptionist],
        "receptionist")
      println("Master node is ready.")
    }
  }
}
