// 코드 6-13 프로그램으로 원격 배포 설정하기

var uri = "akka.tcp://backend@0.0.0.0:2552"
val backendAddress = AddressFromURIString(uri)

val props = Props[BoxOffice].withDeploy(
  Deploy(scope = RemoteScope(backendAddress))
)

context.actorOf(props, "boxOffice")
