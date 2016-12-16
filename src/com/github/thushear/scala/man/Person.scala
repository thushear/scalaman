package com.github.thushear.scala.man

/**
  * Created by kongming on 2016/12/15.
  */
object Person {



  private var eyeNum = 2

  println("this person object!")

  def getEyeName=eyeNum

  def apply(name: String = "thushear", age: Int = 10): Person = new Person(name, age)
}
class Person(name:String = "thushear",age:Int = 10){
  def getName=name

  def sayHi(): Unit ={
    println("my name is " + name + " age is " + age + " has eyes " + Person.eyeNum)
  }
}

class Employee extends Person{

  private var salary = 10;

  def getSalary = salary

  override def getName="hi i am " + super.getName

}
