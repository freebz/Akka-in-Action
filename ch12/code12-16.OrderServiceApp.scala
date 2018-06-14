// 코드 12-16 HTTP 서버 시작하기

object OrderServiceApp extends App
    with RequestTimeout {
  val config = ConfigFactory.load()
  val host = config.getString("http.host")
  val port = config.getInt("http.port")

  implicit val system = ActorSystem()
  implicit val ec = system.dispatcher

  val processOrders = system.actorOf(
    Props(new ProcessOrders), "process-orders"
  )

  val api = new OrderServiceApi(system,
    requestTimeout(config),
    processOrders).routes

  implicit val materializer = ActorMaterializer()
  val bindingFuture: Future[ServerBinding]
  Http().bindAndHandle(api, host, port)

  val log = Logging(system.eventStream, "order-service")
  bindingFuture.map { serverBinding =>
    log.info(s"Bound to ${serverBinding.localAddress} ")
  }.failed.foreach {
    case ex: Exception =>
      log.error(ex, "Failed to bind to {}:{}!", host, port)
      system.terminate()
  }
}
