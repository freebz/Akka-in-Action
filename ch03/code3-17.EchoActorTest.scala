// 코드 3-17 implicitSender

class EchoActorTest extends TeskKit(ActorSystem("testsystem"))
    with WordSpecLike
    with ImplicitSender
    with StopSystemAfterAll {
