package service

import akka.actor.{ActorLogging, ActorRefFactory, Actor}
import spray.http.StatusCodes._
import spray.http.{StatusCodes, HttpRequest, HttpResponse}
import spray.routing.{RoutingSettings, ExceptionHandler, HttpService}
import spray.routing.RoutingSettings

/**
  * Created by shuyun_2 on 2015/12/2.
  */

class MyServiceActor extends Actor with MyService with ActorLogging {

  override implicit def actorRefFactory: ActorRefFactory = context

  def receive = runRoute(myRoute)
}


trait MyService extends HttpService {

  implicit def executionContext = actorRefFactory.dispatcher

  implicit val routingSettings = RoutingSettings.default

  implicit val myExceptionHandler = ExceptionHandler {
    case e: IllegalArgumentException => complete(HttpResponse(BadRequest,"参数错误,要求：菲波拉契参数大于0"))
    case e: Exception =>
      complete(e.getMessage)
  }

  def fibb(n: Int): Long = {
    def fib(n: Int, res1: Long, res2: Long): Long = {
      if (n == 1) res1
      else fib(n - 1, res2, res1 + res2)
    }
    fib(n, 0, 1)
  }


  val myRoute = {
    path("fib" / IntNumber ) {
      fibParam: Int =>
        get {
          require(fibParam > 0)
          complete {
            fibb(fibParam).toString()
          }
        }
    } ~
      path("like") {
        complete("say hi")
      } ~
      path(Rest) {
        pathRest =>
          //    redirect("http://www.baidu.com",StatusCodes.MovedPermanently)
          //      complete(HttpResponse(NotFound),toString)
          //    val ab = NotFound.toString + NotFound.defaultMessage
          //    complete(ab)
          complete(HttpResponse(NotFound,s"你请求的URL: ${pathRest}未找到"))

      }

  }
}

