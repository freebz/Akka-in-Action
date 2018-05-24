// 코드 6-14 백엔드와 프런트엔드를 시작하는 Main 객체

// 백엔드 노드를 시작하는 Main 클래스
object BackendRemoteDeployMain extends App {
  val config = ConfigFactory.load("backend")
  val system = ActorSystem("backend", config)
}

object FrontRemoteDeployMain extends App
    with Startup {
  val config = ConfigFactory.load("frontend-remote-deploy")
  implicit val system = ActorSystem("frontend", config)

  val api = new RestApi() {
    implicit val requestTimeout = configuredRequestTimeout(config)
    implicit def executionContext = system.dispatcher
    def createBoxOffice: ActorRef =
      system.actorOf(
        BoxOffice.props,
        BoxOffice.name
      )
  }

  startup(api.routes)
}
