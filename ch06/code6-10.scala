// 코드 6-10 원격 BoxOffice 검색하기

def createPath(): String = {
  val config = ConfigFactory.load("frontend").getConfig("backend")
  val host = config.getString("host")
  val port = config.getInt("port")
  val protocol = config.getString("protocol")
  val systemName = config.getString("system")
  val actorName = config.getString("actor")
  s"$protocol://$systemName@$host:$port/$actorName"
}

def createBoxOffice: ActorRef = {
  val path = createPath()
  system.actorOf(Props(new REmoteLookupProxy(path)), "lookupBoxOffice")
}
