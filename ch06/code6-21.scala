// 코드 6-21 분산 아키텍처 테스트하기

"A Client Server configured app" must {

  "wait for all nodes to enter a barrier" in {
    enterBarrier("startup")
  }

  "be able to create an event and sell a ticket" in {
    runOn(backedn) {
      system.actorOf(BoxOffice.props(Timeout(1 seconds)), "boxOffice")
      enterBarrier("deployed")
    }

    runOn(frontend) {
      enterBarrier("deployed")

      val path = node(backend) / "user" / "boxOffice"
      val actorSelection = system.actorSelection(path)

      actorSelection.tell(Identify(path), testActor)

      val actorRef = expectMsgPF() {
        case ActorIdentify(`path`, Some(ref)) => ref
      }

      import BoxOffice._

      actorRef ! CreateEvent("RHCP", 20000)

      expectMsg(EventCreated(Event("RHCP", 20000)))

      actorRef ! GetTickets("RHCP", 1)

      expectMsg(Tickets("RHCP", Vector(Ticket(1))))
    }

    enterBarrier("finished")
  }
}
