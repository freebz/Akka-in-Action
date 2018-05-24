// 코드 6-19 다중 노드 테스트를 위한 명세 클래스

class ClientServerSpecMultiJvmFrontend extends ClientServerSpec
class ClientServerSpecMultiJvmBackend extends ClientServerSpec

class ClientServerSpec extends MultiNodeSpec(ClientServerConfig)
    with STMultiNodeSpec with ImplicitSender {

  def initialParticipants = roles.size
}
