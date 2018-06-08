// 코드 11-1 FSM 트레이트에서 처리 과정 정의하기

class Inventory() extends Actor with FSM[State, StateData] {
  startWith(WaitForRequests, new StateData(0,Seq()))

  when(WaitForRequests) {
    case Event(request:BookRequest, data:StateData) => {
      .....
    }

    case Event(PendingRequests, data:StateData) => {
      ...
    }
  }

  ...
}
