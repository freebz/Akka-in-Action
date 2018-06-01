// 코드 10-9 ReliableProxySample 구현

proxy ! "message1"
expectMsg("message1")
Await.ready(
  testConductor.blackhole( client, server, Direction.Both),
  1 second
)

echo ! "DirectMessage"
proxy ! "ProxyMessage"
expectNoMsg(3 seconds)

Await.ready(
  testConductor.passThrough( client, server, Direction.Both),
  1 second
)

expectMsg("ProxyMessage")
echo ! "DirectMessage2"
expectMsg("DirectMessage2")
