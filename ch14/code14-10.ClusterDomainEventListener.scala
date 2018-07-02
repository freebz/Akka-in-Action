// 코드 14-10 Cluster의 도메인 이벤트 구독하기

...
import akka.cluster.{MemberStatus, Cluster}
import akka.cluster.ClusterEvent._

class ClusterDomainEventListener extends Actor with ActorLogging {
  Cluster(context.system).subscribe(self, classOf[ClusterDomainEvent])

  def receive = {
    case MemberUp(member) => log.info(s"$member UP.")
    case MemberExited(member) => log.info(s"$member EXITED.")
    case MemberRemoved(m, previousState) =>
      if(previousState == MemberStatus.Exiting) {
        log.info(s"Member $m gracefully exited, REMOVED.")
      } else {
        log.info(s"$m downed after unreachable, REMOVED.")
      }
    case UnreachableMember(m) => log.info(s"$m UNREACHABLE")
    case ReachableMember(m) => log.info(s"$m REACHABLE")
    case s: CurrentClusterState => log.info(s"cluster state: $s")
  }

  override def postStop(): Unit = {
    Cluster(context.system).unsubscribe(self)
    super.postStop()
  }
}
