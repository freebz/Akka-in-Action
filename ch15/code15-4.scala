// 코드 15-4 계산기 명령과 이벤트

sealed trait Command
case object Clear extends Command
case class Add(value: Double) extends Command
case class Subtrace(value: Double) extends Command
case class Divide(value: Double) extends Command
case class Multiply(value: Double) extends Command
case object PrintResult extends Command
case object GetResult extends Command

sealed trait Event
case object Reset extends Event
case class Added(value: Double) extends Event
case class Subtracted(value: Double) extends Event
case class Divided(value: Double) extends Event
case class Multiplied(value: Double) extends Event
