// 코드 7-4 설정에서 하위 트리 가져오기

val databaseCfg = configuration.getConfig("MyAppl.database")
val databaseConnectString = databaseCfg.getString("connect")
