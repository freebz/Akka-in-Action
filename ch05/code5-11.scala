// 코드 5-11 심각하지 않은 오류들과 매치되는 onFailure 예제

futureFail.onFailure {
  case e => println(e)
}
