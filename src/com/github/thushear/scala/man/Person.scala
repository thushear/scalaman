package com.github.thushear.scala.man

/**
  * Created by kongming on 2016/12/15.
  */
trait Hanlder{
  def handle(data:String){}
}
trait CheckHandler extends Hanlder{
  override def handle(data: String): Unit = {
    println("data check handle")
    super.handle(data)
  }
}

trait SignatureHandler extends Hanlder{
  override def handle(data: String): Unit = {
    println("signature handle")
    super.handle(data)
  }
}

trait Logger{
  def log(message:String) = println(message)
}
trait Logged{
  def logged(message:String){}
}

trait MixinLogger extends Logged{
  override def logged(message: String): Unit = {
    println("log:" + message)
  }
}

trait HiTrait{
  def sayHi(name: String)
}
trait MakeFriendsTrait{
  def makeFriend(p:Person)
}

object Person {



  private var eyeNum = 2

  println("this person object!")

  def getEyeName=eyeNum

  def apply(name: String = "thushear", age: Int = 10): Person = new Person(name, age)
}
class Person(name:String = "thushear",age:Int = 10) extends HiTrait with MakeFriendsTrait with Cloneable with Serializable with Logger with Logged with CheckHandler with SignatureHandler
{
  def getName=name

  def sayHi(): Unit ={
    println("my name is " + name + " age is " + age + " has eyes " + Person.eyeNum)
  }

  override def sayHi(name: String): Unit = {
    println("say hi to " + name)
    log("say hi is invoked " + name)
    logged("hi logged mixedin")
    //Trait调用链
    handle("handler data " + name)
  }

  override def makeFriend(p: Person): Unit = println("my name is " + name + "  frient name is " + p.getName)
}

class Employee extends Person{

  private var salary = 10;

  def getSalary = salary

  override def getName="hi i am " + super.getName

}
