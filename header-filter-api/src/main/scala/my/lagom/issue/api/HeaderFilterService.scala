package my.lagom.issue.api

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}
import my.lagom.issue.code.MyConfigHeaderFilter
import play.api.Configuration

trait HeaderFilterService extends Service {

  def config: Configuration

  override final def descriptor = {
    import Service._
    named("header-filter")
      .withCalls(
        restCall(Method.GET, "/api/sayhello", sayHello)
      )
      .withHeaderFilter(MyConfigHeaderFilter(config))
  }

  def sayHello: ServiceCall[NotUsed, String]
}