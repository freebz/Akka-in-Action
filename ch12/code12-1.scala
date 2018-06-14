// 코드 12-1 카멜이 시작됐는지 확인하기

val camelExtention = CamelExtension(system)
val activated = camelExtention.activationFutureFor(
  consumer)(timeout = 10 seconds, executor = system.dispatcher)
Await.ready(activated, 5 seconds)
