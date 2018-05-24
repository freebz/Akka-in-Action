// 코드 6-7 핵심 액터 코드에서 중요한 부분

object SingleNodeMain extends App
    with Startup {

  val api = new RestApi() {

    ...

    def createBoxOffice: ActorREf = system.actorOf(BoxOffice.props,
      BoxOffice.name)
  }

  startup(ari.routes)
}

object FrontendMain extends App
    with Startup {

                 ...

  val api = new RestApi() {

                 ...

    def createPath(): String =

                 ...

    def createBoxOffice: ActorRef = {
      val path = createPath()
      system.actorOf(Props(new RemoteLookupProxy(path)), "lookupBoxOffice")
    }
  }

  startup(api.routes)
}

object BackendMain extends App with RequestTimeout {

                 ...

  system.actorOf(BoxOffice.props, BoxOffice.name)
}
