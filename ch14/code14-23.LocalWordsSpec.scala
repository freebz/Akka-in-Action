// 코드 14-23 words 앱의 로컬 테스트

class LocalWordsSpec extends TestKit(ActorSystem("test"))
                        with WordSpecLike
                        with MustMatchers
                        with StopSystemAfterAll
                        with ImplicitSender {

  val receptionist = system.actorOf(Props[TestReceptionist],
                                    JobReceptionist.name)
  "The words system" must {
    "count the occurrence of words in a text" in {
      receptionist ! JobRequest("test2", List("this is a test ",
                                              "this is a test",
                                              "this is",
                                              "this"))
      expectMsg(JobSuccess("test2", Map("this" -> 4,
                                        "is"   -> 3,
                                        "a"    -> 2,
                                        "test" -> 2)))
      expectNoMsg
    }

    ...

    "continue to process a job with intermittent failures" in {
      receptionist ! JobRequest("test4", List("this", "is", "a", "test", "FAIL!"))
      expectMsg(JobSuccess("test4", Map("this" -> 1,
                                        "is"   -> 1,
                                        "a"    -> 1,
                                        "test" -> 1)))
      expectNoMsg
    }
  }
}
