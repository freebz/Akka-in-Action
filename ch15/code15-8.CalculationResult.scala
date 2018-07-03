// 코드 15-8 계산을 수행하고 다음 상태를 반환하기

case class CalculationResult(result: Double = 0) {
  def reset = copy(result = 0)
  def add(value: Double) = copy(result = this.result + value)
  def subtract(value: Double) = copy(result = this.result - value)
  def divide(value: Double) = copy(result = this.result / value)
  def multiply(value: Double) = copy(result = this.result * value)
}
