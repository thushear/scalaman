package com.github.thushear.scala.man
import java.io._
import javax.swing._
import java.awt.event._
import java.io.FileNotFoundException


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

    println("==========模式匹配=================")
    //模式匹配
    judgeGrade("A")
    judgeGrade("B")
    judgeGrade("C")
    judgeGrade("D")
    judgeGrade("G")

    processException(new IllegalArgumentException("illegal args"))
    processException(new FileNotFoundException("file not found"))
    processException(new IOException("io error"))
    processException(new IndexOutOfBoundsException("index out bound"))

    arrayMatch(Array[String]("thushear"))
    arrayMatch(Array[String]("thushear","ann","kathiran"))
    arrayMatch(Array[String]("thushear","ann","kathiran","anna"))

    listMatch(List[String]("thushear"))
    listMatch(List[String]("thushear","ann","kathrian"))
    listMatch(List[String]("thushear","ann","kathrian","anna"))

    caseClassMatch(MTeacher("thus","math"))
    caseClassMatch(MStudent("thus","1"))
    caseClassMatch(MStudent("thus","1"))

    optionMatch("jack")
    optionMatch("kerouac")

    //泛型
    //泛型
    class TypeStudent[T](val localId:T) {

       def getId( licenseId:T ) = "ss - " + licenseId + "-" + localId

    }
    val st  = new TypeStudent[Int](123)
    println(st.getId(1222))

    // 上Bounds
    class BPerson(val name :String){
      def sayHi = println("hello my name is " + name)
      def makeFriend(p:BPerson): Unit ={
        sayHi
        p.sayHi
      }
    }

    class BStudent(name:String) extends BPerson(name)

    class BParty[T <: BPerson](p1:T,p2:T){
      def play = p1.makeFriend(p2)
    }

    val bparty = new BParty[BPerson](new BStudent("ss"),new BPerson("tt"))
    bparty.play

    //Manifest Context Bounds
    class Meat(val name:String)
    class Vegetable(val name:String)

//    def packageFood[T:Manifest](foods:T*): Unit ={
//      val foodPackage = new Array[T](foods.length)
//
//      for( i  <-  0 until  foods.length) {
//        foodPackage[i] = foods[i]
//      }
//      foodPackage
//    }
//
//    packageFood(new Meat("dog"),new Meat("pig"))


    class SpecialPerson(val name:String)
    class Older(val name:String)
    class SStudent(val name:String)

    implicit def obj2SpecialPerson(obj:Object):SpecialPerson = {
      if (obj.getClass == classOf[Older]){
         val older = obj.asInstanceOf[Older]
         new SpecialPerson(older.name)
      }else if (obj.getClass == classOf[SStudent]){
        val stu = obj.asInstanceOf[SStudent]
        new SpecialPerson(stu.name)
      }else {
        null
      }
    }

    var ticket = 0
    def buySpecial(p:SpecialPerson) ={
      ticket += 1
      "t-" + ticket
    }



    println(buySpecial(new Older("tttooo")))
    println(buySpecial(new SStudent("anna")))

    //隐式参数
    class SignPen{
      def write(content:String) = println(content)
    }

    implicit val signPen = new SignPen

    def sign(name:String)(implicit signPen: SignPen): Unit ={
      signPen.write(name)
    }

    sign("implicit params")

    //Actor

    class HelloActor extends



  }

  //模式匹配实例
  def judgeGrade(grade: String): Unit ={
     grade match {
       case "A" => println("you are excellent")
       case "B" => println("you are so good ")
       case "C" => println("just so so ")
       case _grade if grade == "G" => println("you need to think your grade is " + _grade )
       case _grade => println("you need to worker harder your grade is " + _grade)
     }





  }


  //对类型进行模式匹配
  def processException(e : Exception): Unit ={
    e match {
      case e1: IllegalArgumentException => println("your params has error " + e1)
      case e2: FileNotFoundException => println("file cannot found your exception is " + e2)
      case e3: IOException => println("IO Error " + e3)
      case _e: Exception => println("other error " + _e)
    }
  }

  //数组模式匹配
  def arrayMatch(arr: Array[String]): Unit ={
    arr match {
      case Array("thushear") => println("hi thushear")
      case Array(a1,a2,a3) => println("three " + a1 + ":" + a2 + ":" + a3)
      case Array("thushear",_*) =>println("hi thus")
      case _ => println("who are you ")
    }
  }

  // 列表模式匹配
  def listMatch(list : List[String]): Unit ={

    list match {
      case "thushear"::Nil => println("one elem List ")
      case l1::l2::l3::Nil => println("three elem " + l1 + ":" + l2 + ":" + l3)
      case "thushear"::tail => println("hi thus")
      case _ => println("who are you")
    }

  }

  //Case class 模式匹配
  class MPerson
  case class MStudent(name:String,classRoom:String) extends MPerson
  case class MTeacher(name:String,subject:String) extends MPerson
  case class MWorker(name:String ) extends MPerson
  def caseClassMatch(p:MPerson): Unit ={
    p match {
      case MTeacher(name,subject) => println("teacher " + name  + "teach " + subject)
      case MStudent(name,classRoom) => println("student " + name + " in class " + classRoom)
      case _ => println("not allow to in")
    }
  }

  val map = Map("jack"->10,"leo" -> 11,"ann"->12)
  //Option模式匹配
  def optionMatch(name:String): Unit ={
    val grade = map.get(name)
    grade match {
      case Some(grade) => println("your grade is " + grade)
      case None => println("you are not here")
    }
  }




}
