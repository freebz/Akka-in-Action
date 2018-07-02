// 코드 14-14 클러스터화 BroadcastPool 라우터 만들기

trait CreateWorkerRouter { this: Actor =>
  def createWorkerRouter: ActorRef = {
    context.actorOf(
      ClusterRouterPool(BroadcastPool(10),
        ClusterRouterPoolSettings(
          totalInstances = 1000,
          maxInstancesPerNode = 20,
          allowLocalRoutees = false,
          useRole = None
        )
      ).props(Props[JobWorker]),
      name = "worker-router")
  }
}
