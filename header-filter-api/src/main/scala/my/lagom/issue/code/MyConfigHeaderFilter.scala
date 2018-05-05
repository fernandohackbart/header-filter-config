package my.lagom.issue.code

import com.lightbend.lagom.scaladsl.api.transport.{HeaderFilter, RequestHeader, ResponseHeader}
import play.api.Configuration

object MyConfigHeaderFilter extends HeaderFilter {

  override def transformServerRequest(request: RequestHeader) = {
//    val parameter = configuration.get[String]("cle")
//    log.debug(s"Configuration: ${config.keys} ")
    println(s"MyHeaderFilter executed")
    request
  }

  override def transformClientRequest(request: RequestHeader) = request

  override def transformServerResponse(response: ResponseHeader, request: RequestHeader) = response

  override def transformClientResponse(response: ResponseHeader, request: RequestHeader) = response

}

class MyConfigHeaderFilter(configuration: Configuration) extends HeaderFilter {

  override def transformServerRequest(request: RequestHeader) = {
    val parameter = configuration.get[String]("my.parameter")
    println(s"MyConfigHeaderFilter parameter value: ${parameter} \n")
    request
  }

  override def transformClientRequest(request: RequestHeader) = request

  override def transformServerResponse(response: ResponseHeader, request: RequestHeader) = response

  override def transformClientResponse(response: ResponseHeader, request: RequestHeader) = response

}