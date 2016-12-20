package com.github.thushear.scala.man

import javax.swing._
import java.awt.event._
/**
  * Created by kongming on 2016/12/15.
  */
object ScalaMan {

  def main(args: Array[String]): Unit = {
//    println("hello world!")
    val  c = new ClassMan("titan")
    c.sayHi()
    println(c.getName)
    println(c.age)
    c.age=11
    println(c.age)

    println(c.sex)
    c.sex="female"
    println(c.sex)

    println(c.getWork)
    c.setWork("programmer")
    println(c.getWork)

    val stu = c.getStudent("thushear")
    c.students += stu
    println(c.students.toString())

    println(Person.getEyeName)
    println(Person.getEyeName)

    val p = new Person()
    p.sayHi()
    //apply
    val pp = Person("thus")
    pp.sayHi()

    val  e = new Employee

    println(e.getName)

    val ppp:Person = new Employee

    var ee: Employee = null

    if(ppp.isInstanceOf[Employee]){
      ee = ppp.asInstanceOf[Employee]
      ee.getName
    }

    val pppp = Person("thus")
    val p5 = Person("hear")
    pppp.sayHi("trait")
    pppp.makeFriend(p5)

    val p6 = new Person("ttt") with MixinLogger
    p6.sayHi("mixin")

    //函数式编程
    def doSomething(name:String){
      println("doSomeThing " + name)
    }

    val doSomethingFunc = doSomething _

    doSomethingFunc("some")

    //匿名函数
    val doAnotherthingFunc = (name:String)=>println("doAnotherthingFunc " + name)
    doAnotherthingFunc("another")

    //高阶函数
    def greeting(func:(String)=>Unit,name:String): Unit ={
      func(name)
    }
    greeting(doSomethingFunc,"leo")

    println(Array(1,2,4,6).map((num:Int)=>num * num).toString)


    println(Array(1,2,3,4,5,6,7).map(2 * _).toString)

    Array(1,2,4,6).map((num:Int)=>num * num).foreach(println _)
    Array(1,2,3,4,5,6,7).map(2 * _).foreach(println _)

    (1 to 9).map("*" * _).foreach(println _)

    (1 to 20).filter(_ % 2 == 0).foreach(println _)

    (1 to 11).reduceLeft(_ * _).toString

    //闭包
    def getGreeting(msg:String) = (name:String) => println(msg + " to " + name)
    val greetingFunc = getGreeting("hello")
    greetingFunc("bibao")

    val button = new JButton("click")
    button.addActionListener(new ActionListener {
      override def actionPerformed(e: ActionEvent): Unit = {
        println("click me!!!")
      }
    })

    def sum(a:Int) = (b:Int) => a + b
    val summ = sum(1)(2)
    println(summ)

    def decorator(l:List[Int],prefix:String): Unit ={
      if (l != Nil){
        println(prefix + l.head)
        decorator(l.tail,prefix)
      }
    }
    decorator(List(1,2,3,4,5),"hi ")

    val list = scala.collection.mutable.LinkedList(1,2,3,4,5)
    var currentList = list
    while(currentList != Nil){
      currentList.elem = currentList.elem * 2
      currentList = currentList.next
    }
    println(currentList)
    println(list)

    println("===========================")
    List("hello world","thushear github").flatMap(_.split(" ")).foreach(println _)

    List("thusher","leo").zip(List(100,88)).foreach(println _)

    println("===========================")
    val line = scala.io.Source.fromFile("D:\\codingnet\\scalaman\\src\\resources\\input.txt").mkString
    println(List(line).flatMap(_.split(" ")).map((_,1)).map(_._2).reduceLeft(_+_))
    List(line).flatMap(_.split(" ")).map((_,1)).foreach(println _)
    List(line).flatMap(_.split(" ")).map((_,1)).map(_._2).foreach(println _)
  }
}
