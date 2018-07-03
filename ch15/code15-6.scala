// 코드 15-6 일반 명령을 처리하는 receiveCommand 수신 함수

val receiveCommand: Receive = {
  case Add(value)      => persist(Added(value))(updateState)
  case Subtract(value) => persist(Subtracted(value))(updateState)
  case Divide(value)   => if(value != 0) persist(Divided(value))(updateState)
  case Multiply(value) => persist(Multiplied(value))(updateState)
  case PrintResult     => println(s"the result is: ${state.result}")
  case GetResult       => sender() ! state.result
  case Clear           => persist(Reset)(updateState)
}
