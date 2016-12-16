package com.github.thushear.scala.man

import scala.beans.BeanProperty
import scala.collection.mutable.ArrayBuffer

/**
  * Created by kongming on 2016/12/15.
  */
class ClassMan {


  class Student(name: String){
    println("name = " + name)
  }

  var students = new ArrayBuffer[Student]

  def getStudent(name:String)= new Student(name)

  def this(name: String){
    this()
    this.name = name
  }

  var age = 10;

  private var name = "thushear";

  private var  mySex : String = "male";

   @BeanProperty var work:String=_

  def sex="your sex is " + mySex
  def sex_=(newSex: String){
    println("your new sex is " + newSex)
  }

  def sayHi(): Unit ={
    println("hello " + name)
  }

  def getName = name


}
