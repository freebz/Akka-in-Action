// 코드 6-18 STMultiNodeSpec을 scalatest에 연결하기

import akka.remote.testkit.MultiNodeSpecCallbacks
import org.scalatest.{BeforeAndAfterAll, WordSpec}
import org.scalatest.matchers.MustMatchers

trait STMultiNodeSpec extends MultiNodeSpecCallbacks
    with WordSpec with MustMatchers with BeforeAndAfterAll {

  override def beforeAll() = multiNodeSpceBeforeAll()

  override def afterAll() = multiNodeSpecAfterAll()
}
