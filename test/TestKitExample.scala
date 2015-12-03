import akka.actor.{ActorRefFactory, Props, ActorSystem}
import akka.testkit.TestProbe
import org.scalacheck.{Prop, Gen}
import org.scalatest.WordSpec
import org.scalatest.prop.PropertyChecks
import service.{MyServiceActor, MyService}
import spray.http.HttpResponse
import spray.routing.HttpService
import spray.testkit.ScalatestRouteTest
import spray.util.Utils


/**
  * Created by shuyun_2 on 2015/12/3.
  */
class TestKitExample extends WordSpec with ScalatestRouteTest with MyService with PropertyChecks {
  override implicit def actorRefFactory: ActorRefFactory = system

  "MyService" should {
    "/fib/{number} 针对接口，当number大于0时，为正常路径，正常路径的访问测试" in {
      val evenInts = for (n <- Gen.choose(1, 100)) yield n
      forAll(evenInts) { n: Int =>
        println(n)
        Get(s"/fib/$n") ~> myRoute ~> check {
          assert(responseAs[String] == fibb(n).toString)
        }
      }
    }
    "/fib/{number} 针对接口，当Number为0时，错误路径访问测试" in {
      Get("/fib/0") ~> myRoute ~> check {
        assert(response.status.intValue == 400)
      }
    }
    "针对/fib/  针对接口，当路经后为负数时，错误路径访问测试" in {
      val evenInts = for (n <- Gen.choose(-100,-1)) yield n
      forAll(evenInts) { n: Int =>
        Get(s"/fib/$n") ~> myRoute ~> check {
          assert(response.status.intValue == 404)
        }
      }
    }
    "针对/fib/  针对接口，当路径后为字母时，错误路径访问测试" in {
      forAll{s:String=>
        whenever("[a-zA-Z0-9]".r.pattern.matcher(s).find()){
          println(s)

        }
      }
    }
  }
}


/*
"The service" - {

  "should return a greeting for GET requests to the root path" in {
    Get() ~> smallRoute ~> check {
      assert(responseAs[String].contains("Say hello"))
    }
  }
*/


/* def actorRefFactory = system // connect the DSL to the test ActorSystem



 val smallRoute =
   get {
     pathSingleSlash {
       complete {
         <html>
           <body>
             <h1>Say hello to <i>spray</i>!</h1>
           </body>
         </html>
       }
     } ~
       path("ping") {
         complete("PONG!")
       }
   }

 "The service" - {

   "should return a greeting for GET requests to the root path" in {
     Get() ~> smallRoute ~> check {
       assert(responseAs[String].contains("Say hello"))
     }
   }

 }*/


