// 코드 11-6 FSM 초기화하기

class Inventory(publisher:ActorRef) extends Actor
    with FSM[State, StateData] {

  startWith(WaitForRequests, new StateData(0,Seq()))

  when...

  onTransition...

  initialize
}
