// 코드 5-9 퓨처에서 예외 발생시키기

import scala.concurrent._
import ExecutionContext.Implicits.global

val futureFail = Future { thorw new Exception("error!")}
futureFail.foreach(value => println(value))
