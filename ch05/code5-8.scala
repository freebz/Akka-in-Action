// 코드 5-8 Promise를 사용해 Future를 돌려주는 API 만들기

def sendToKafka(record: ProducerRecord): Future[RecordMetadata] = {
  val promise: Promise[RecordMetadata] = Promise[RecordMetadata]()
  val future: Future[RecordMetadata] = promise.future

  val callback = new Callback() {
    def onCompletion(metadata: RecordMetadata, e: Exception): Unit = {
      if (e != null) promise.failure(e)
      else promise.success(metadata)
    }
  }
  producer.send(record, callback)
  future
}
