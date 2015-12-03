package controllers

import play.api.mvc.{Action, Controller}

/**
  * Created by shuyun_2 on 2015/12/1.
  */
class MyControl extends Controller{

  def hello = Action(Ok("controller: Hello,world"))

}
