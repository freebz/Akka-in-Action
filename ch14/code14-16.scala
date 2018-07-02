// 코드 14-16 클러스터 안의 모든 JobReceptionist에게 메시지 보내기

val receptionistRouter = context.actorOf(
  ClusterRouterGroup(
    BroadcastGroup(Nil),
    ClusterRouterGroupSettings(
      totalInstances = 100,
      routeesPaths = List("/user/receptionist"),
      allowLocalRoutees = true,
      useRole = Some("master")
    )
  ).props(),
  name = "receptionist-router")
ClusterRouterGroup - ClusterRouterGroup
