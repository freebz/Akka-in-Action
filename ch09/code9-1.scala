// 코드 9-1 설정 파일ㅇㄹ 사용해 라우터 만들기

val router = system.actorOf(
  FromConfig.props(Props(new GetLicense(endProbe.ref))),
  "poolRouter"
)
