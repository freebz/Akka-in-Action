// 코드 9-9 라우티 종료 시 새 액터 만들기

class GetLicenseCreator(nrActors: Int) extends Actor {

  override def preStart() {
    super.preStart()
    (0 until nrActors).map(nr => {
      val child = context.actorOf(
        Props(new GetLicense(nextStep)), "GetLicense"+nr)
      context.watch(child)
    })
  }

  def receive = {
    case Terminated(child) => {
      val newChild = context.actorOf(
        Props(new GetLicense(nextStep)), child.path.name)
      context.watch(newChild)
    }
  }
}
