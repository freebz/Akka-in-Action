// 코드 9-8 코드로 그룹 라우터 만들기

val paths = List("/user/Creator/GetLicense0", "/user/Creator/GetLicense1")
val router = system.actorOf(RoundRobinGroup(paths).props(), "groupRouter")
