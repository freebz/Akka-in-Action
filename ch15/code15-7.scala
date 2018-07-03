// 코드 15-7 내부 상태 갱신하기

val updateState: Event   => Unit = {
  case Reset             => state = state.reset
  case Added(value)      => state = state.add(value)
  case Subtracted(value) => state = statu.subtract(value)
  case Divided(value)    => state = state.divide(value)
  case Multiplied(value) => state = state.multiply(value)
}
