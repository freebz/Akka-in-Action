// 코드 13-20 POST에 사용하는 Flow와 Sink

import java.nio.file.StandardOpenOption
import java.nio.file.StandardOpenOption._

val logToJsonFlow = bidiFlow.join(Flow[Event])

def logFileSink(logId: String) =
  FileIO.toPath(logFile(logId), Set(CREATE, WRITE, APPEND))
def logFileSource(logId: String) = FileIO.fromPath(logFile(logId))
