// 코드 6-17 테스트할 노드의 역할을 기술함

object ClientServerConfig extends MultiNodeConfig {
  val frontend = role("frontend")
  val backend = role("backend")
}
