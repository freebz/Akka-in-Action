// 코드 14-7 시드 노드 1의 재가입을 위해 액터 시스템을 다시 시자하기

scala> seedSystem.terminate()
scala> val seedSystem = ActorSystem("words", seedConfig)
