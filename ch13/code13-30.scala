// 코드 13-30 POST 루트에 processStates 사용하기

src.via(processStates(logId))
  .toMat(logFileSink(logId))(Keep.right)
  .run()
