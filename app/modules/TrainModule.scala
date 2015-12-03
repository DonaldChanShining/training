package modules



import main.TrainMain
import com.google.inject.AbstractModule
import play.api.libs.concurrent.AkkaGuiceSupport
import service.MyServiceActor


/**
  * Created by shuyun_2 on 2015/12/1.
  */
class TrainModule extends AbstractModule with AkkaGuiceSupport{
  override def configure(): Unit = {
    bind(classOf[TrainMain]).asEagerSingleton()
    bindActor[MyServiceActor]("myService")
  }

}
