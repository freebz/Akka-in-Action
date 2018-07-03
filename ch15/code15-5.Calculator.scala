// 코드 15-5 영속성 액터를 확장하고 persistenceId 정의하기

class Calculator extends PersistentActor with AcotrLogging {
  import Calculator._

  def persistenceId = Calculator.name
  // 나머지 코드
