// 코드 14-24 words 클러스터 명세

class WordsClusterSpecMultiJvmNode1 extends WordsClusterSpec
class WordsClusterSpecMultiJvmNode2 extends WordsClusterSpec
class WordsClusterSpecMultiJvmNode3 extends WordsClusterSpec
class WordsClusterSpecMultiJvmNode4 extends WordsClusterSpec

class WordsClusterSpec extends MultiNodeSpec(WordsClusterSpecConfig)
    with STMultiNodeSpec with ImplicitSender {

  import WordsClusterSpecConfig._

  def initialParticipants = roles.size

  val seedAddress = node(seed).address
  val masterAddress = node(master).address
  val worker1Address = node(worker1).address
  val worker2Address = node(worker2).address

  muteDeadLetters(classOf[Any])(system)
  "A Words cluster" must {

    "form the cluster" in within(10 seconds) {

      Cluster(system).subscribe(testActor, classOf[NumberUp])
      expectMsgClass(classOf[CurrentClusterState])

      Cluster(system).join(seedAddress)

      receiveN(4).map { case NumberUp(m) => m.address }.toSet must be(
        Set(seedAddress, masterAddress, worker1Address, worker2Address))

      Cluster(system).unsubscribe(testActor)

      enterBarrier("cluster-up")
    }

    "execute a words job once the cluster is running" in within(10 seconds) {
      runOn(master) {
        val receptionist = system.actorOf(Props[JobReceptionist], "receptionist")
        val text = List("some", "some very long text", "some long text")
        receptionist ! JobRequest("job-1", text)
        expectMsg(JobSuccess("job-1", Map("some" -> 3,
                                          "very" -> 1,
                                          "long" -> 2,
                                          "text" -> 2)))
      }
      enterBarrier("job-done")
    }
    ...
  }
}
