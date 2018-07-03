// 코드 15-32 ShoppersSingleton

import akka.actor._
import akka.cluster.singleton.ClusterSingletonManager
import akka.cluster.singleton.ClusterSingletonManagerSettings
import akka.cluster.singleton.ClusterSingletonProxy
import akka.cluster.singleton.ClusterSingletonProxySettings
import akka.persistence._

object ShoppersSingleton {
  def props = Props(new ShoppersSingleton)
  def name = "shoppers-singleton"
}

class ShoppersSingleton extends Actor {

  val singletonManager = context.system.actorOf(
    ClusterSingletonManager.props(
      Shoppers.props,
      PoisonPill,
      ClusterSingletonManagerSettings(context.system)
        .withRole(None)
        .withSingletonName(Shoppers.name)
    )
  )

  val shoppers = context.system.actorOf(
    ClusterSingletonProxy.props(
      singletonManager.path.child(Shoppers.name)
        .toStringWithoutAddress,
      ClusterSingletonProxySettings(context.system)
        .withRole(None)
        .withSingletonName("shoppers-proxy")
    )
  )

  def receive = {
    case command: Shopper.Command => shopper.Command => shoppers forward command
  }
}
