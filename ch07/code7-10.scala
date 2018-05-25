// 코드 7-10 설정 지정하기

val configuration = ConfigFactory.load("myapp")
val systemA = ActorSystem("mysystem",configuration)
