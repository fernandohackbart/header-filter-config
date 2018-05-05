package my.lagom.issue.impl

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.Materializer
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import com.lightbend.lagom.scaladsl.server.{LagomApplication, LagomApplicationContext, LagomApplicationLoader}
import my.lagom.issue.api
import my.lagom.issue.api.HeaderFilterService
import play.api.Configuration
import com.softwaremill.macwire.wire
import play.api.libs.ws.ahc.AhcWSComponents

import scala.concurrent.{ExecutionContext, Future}

class HeaderFilterServiceLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext) =
    new HeaderFilterApplication(context) with LagomDevModeComponents

  override def loadDevMode(context: LagomApplicationContext) =
    new HeaderFilterApplication(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[api.HeaderFilterService])
}

abstract class HeaderFilterApplication(context: LagomApplicationContext)
  extends LagomApplication(context) with AhcWSComponents {

  override lazy val lagomServer = serverFor[HeaderFilterService](wire[HeaderFilterServiceImpl])

}

class HeaderFilterServiceImpl(configuration: Configuration,
                        system: ActorSystem)(implicit ec: ExecutionContext,
                                             mat: Materializer) extends api.HeaderFilterService {

  override def sayHello: ServiceCall[NotUsed, String] = { req =>
    val parameter = configuration.get[String]("my.parameter")

    println(s"HeaderFilterServiceImpl: parameter= ${parameter}")
    Future("Hello\n")
  }
}
