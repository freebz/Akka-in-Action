// 코드 3-12 FilteringActor 구현

"filter out particular messages using expectNoMsg" in {
  import FilteringActor._
  val props - FilteringActor.props(testActor, 5)
  val filter = system.actorOf(props, "filter-2")

  filter ! Event(1)
  filter ! Event(2)
  expectMsg(Event(1))
  expectMsg(Event(2))
  filter ! Event(1)
  expectNoMsg
  filter ! Event(3)
  expectMsg(Event(3))
  filter ! Event(1)
  expectNoMsg
  filter ! Event(4)
  filter ! Event(5)
  filter ! Event(5)
  expectMsg(Event(4))
  expectMsg(Event(5))
  expectNoMsg()
}
