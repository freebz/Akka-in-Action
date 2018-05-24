// 코드 6-6 actorSelection 사용하기

val path = "akka.tcp://backend@0.0.0.0:2551/user/simple"
val simple = frontend.actorSelection(path)
