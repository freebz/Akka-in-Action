// 코드 9-11 그룹의 라우티 크기를 바꾸는 라우티 생성 액터 예제

class DynamicRouteeSizer(nrActors: Int,
                         props: Props,
                         router: ActorRef) extends Actor {
  var nrChildren = nrActors
  var childInstanceNr = 0

  // 자식들 재시작시키기
  override def preStart() {
    super.preStart()
    (0 until nrChildren).map(nr => createRoutee())
  }

  def createRoutee() {
    childInstanceNr += 1
    val child = context.actorOf(props, "routee" + childInstanceNr)
    val selection = context.actorSelection(child.path)
    router ! AddRoutee(ActorSelectionRoutee(selection))
    context.watch(child)
  }

  def receive = {
    case PreferredSize(size) => {
      if (size < nrChildren) {
        // 라우티 제거하기
        context.children.take(nrChildren - size).foreach(ref => {
          val selection = context.actorSelection(ref.path)
          router ! RemoveRoutee(ActorSelectionRoutee(selection))
        })
        router ! GetRoutees
      } else {
        (nrChildren until size).map(nr => createRoutee())
      }
      nrChildren = size
    }
    case routees: Routees => {
      // Routees를 actorPath로 변환하기
      import collection.JavaConverters._
      var active = routees.getRoutees.asScala.map{
        case x: ActorRefRoutee => x.ref.path.toString
        case x: ActorSelectionRoutee => x.selection.pathString
      }
      // 라우티 목록 처리하기
      for(routee <- context.children) {
        val index = active.indexOf(routee.path.toStringWithoutAddress)
        if (index >= 0) {
          active.remove(index)
        } else {
          // 라우터가 더 이상 자식을 사용하지 않는다.
          routee ! PoisonPill
        }
      }
      // active에는 종료된 라우티들이 들어있다.
      for (terminated <- active) {
        val name = terminated.substring(terminated.lastIndexOf("/")+1)
        val child = context.actorOf(props, name)
        context.watch(child)
      }
    }
    case Terminated(child) => router ! GetRoutees
  }
}
