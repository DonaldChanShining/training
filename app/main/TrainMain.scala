package main


import javax.inject.Inject
import javax.inject.{Named, Inject}

import _root_.service.MyServiceActor
import akka.actor.{ActorRef, ActorSystem}
import akka.io.IO
import akka.pattern.AskTimeoutException
import play.api.http.HttpRequestHandler
import service.MyServiceActor
import shapeless.Zipper.Get
import spray.can.Http
import spray.http.HttpResponse
import spray.http.StatusCodes._
import spray.routing.{ExceptionHandler, SimpleRoutingApp}
import akka.actor._

/**
  * Created by shuyun_2 on 2015/12/1.
  */
class TrainMain(){
//class TrainMain @Inject()(@Named("myListener") myListener: ActorRef)  {

  implicit val system: ActorSystem = ActorSystem("my-system")

  val myService = system.actorOf(Props[MyServiceActor],"my-service")


  // val myListener : ActorRef
  //myListener ! "hello"

  myService !  "hello"

  //IO(Http)(system) ! Http.Bind(myListener, interface = "localhost", port = 8080)
  IO(Http)(system) ! Http.Bind(myService,interface = "localhost",port = 8090)

  /* IO(Http)(system) ! Http.Bind(myListener, interface = "localhost", port = 10086)
   logger.info(s"Server started listening request on port $port")*/

}
