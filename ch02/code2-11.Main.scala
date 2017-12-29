// 코드 2-11 Main 클래스의 임포트문

import akka.actor.{ ActorSystem , Actor, Props }
import akka.event.Logging
import akka.util.Timeout

import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import com.typesafe.config.{ Config, ConfigFactory }
