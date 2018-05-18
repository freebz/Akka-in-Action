// 코드 5-10 onComplete를 사용해 성공과 실패 처리하기

import scala.util._
import scala.concurrent._
import ExecutionContext.Implicits.global

val futureFail = Future { throw new Exception("error!")}
futureFail.onComplete {
  case Success(value) => println(value)
  case Failure(e) => println(e)
}


// java.lang.Exception: error!
