// 코드 9-6 라우티를 만드는 GetLicenseCreator 액터

class GetLicenseCreator(nrActors: Int) extends Actor {

  override def preStart() {
    super.preStart()
      (0 until nrActors).map { nr =>
        context.actorOf(Props[GetLicense], "GetLicense"+nr)
        system.actorOf(Props( new GetLicenseCreator(2)),"Creator")
      }
  }
  ...
}

system.actorOf(Props( new GetLicenseCreator(2)),"Creator")
