// 코드 15-30 Shopper 액터

import akka.actor._

object Shopper {
  def props(shopperId: Long) = Props(new Shopper)
  def name(shopperId: Long) = shopperId.toString

  trait Command {
    def shopperId: Long
  }

  case class PayBasket(shopperId: Long) extends Command
  // 단순화해서 모든 고객이 4만 원을 쓰게 한다.
  val cash = 40000
}

class Shopper extends Actor {
  import Shopper._

  def shopperId = self.path.name.toLong

  val basket = context.actorOf(Basket.props,
    Basket.name(shopperId))

  val wallet = context.actorOf(Wallet.props(shopperId, cash),
    Wallet.name(shopperId))

  def receive = {
    case cmd: Basket.Command => basket forward cmd
    case cmd: Wallet.Command => wallet forward cmd

    case PayBasket(shopperId) => basket ! Basket.GetITems(shopperId)
    case Items(list) => wallet ! Wallet.Pay(list, shopperId)
    case paid: Wallet.Paid =>
      basket ! Basket.Clear(paid.shopperId)
      context.system.eventStream.publish(paid)
  }
}
