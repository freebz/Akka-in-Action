// 코드 7-11 실행 중인 앱에서 설정 값 가져오기

val mySystem = ActorSystem("myAppl")
val config = mySystem.settings.config
val applicationDescription = config.getString("MyAppl.name")
