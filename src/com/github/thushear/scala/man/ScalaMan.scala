package com.github.thushear.scala.man

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

  }
}
