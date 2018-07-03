// 코드 15-9 복구를 위한 receiveRecover 수신 함수

val receiveRecover: Receive = {
  case event: Event => updateState(event)
  case RecoveryCompleted => log.info("Calculator recovery completed")
}
