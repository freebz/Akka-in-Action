// 코드 14-22 별도의 트레이트를 활용해 로컬에서 테스트하기

trait CreateLocalWorkerRouter extends CreateWorkerRouter { this: Actor =>

  override def createWorkerRouter: ActorRef = {
    context.actorOf(BroadcastPool(5).props(Props[JobWorker]),
      "worker-router")
  }
}

class TestJobMaster extends JobMaster
                       with CreateLocalWorkerRouter

class TestReceptionist extends JobReceptionist
                          with CreateMaster {
  override def createMaster(name: String): ActorRef = {
    context.actorOf(Props[TestJobMaster], name)
  }
}
