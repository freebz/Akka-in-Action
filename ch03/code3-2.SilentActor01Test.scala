// 코드 3-2 SilentActor 유형 테스트

class SilentActor01Test extends TestKit(ActorSystem("testsystem"))
    with WordSpecLike
    with MustMatchers
    with StopSystemAfterAll {

  "A Silent Actor" must {
    "change state when it receives a message, single threaded" in {
      // 테스트를 작성한다. 일단 실패한다.
      fail("not implemented yet")
    }
    "change state when it receives a message, multi-threaded" in {
      // 테스트를 작성한다. 일단 실패한다.
      fail("not implemented yet")
    }
  }
}
