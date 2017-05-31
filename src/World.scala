import akka.actor.{ActorSystem, Props}
import akka.pattern.{FutureRef, ask}
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.Random

/**
  * Created by Oliver on 19.05.2017.
  */
class World {
  val width = 50
  val height = 50
  val cellsize = 10
  var singlestep = false
  var timeser: Long = 0
  var timepar: Long = 0

  var world = Array.ofDim[Boolean](width, height)

  val system = ActorSystem("GenerationSystem")
  val one = system.actorOf(Props[NextGenActor], name = "one")
  val two = system.actorOf(Props[NextGenActor], name = "two")
  val three = system.actorOf(Props[NextGenActor], name = "three")

  val mes1 = new actormessage
  val mes2 = new actormessage
  val mes3 = new actormessage
  val mes4 = new actormessage

  implicit val timeout = new Timeout(Duration.create(1, "seconds"))

  populate()

  def populate(): Unit = {
    val rand = new Random
    for(i <- 0 to width - 1; j <- 0 to height - 1) {
      world(i)(j) = rand.nextBoolean()
    }
  }

  def countneighbours(x: Int, y: Int): Int = {
    var count = 0

    if(y != 0 && y != height - 1){
      if(x != 0 && x != width - 1){
        if(world(x-1)(y-1)) count += 1
        if(world(x)(y-1)) count += 1
        if(world(x+1)(y-1)) count += 1
        if(world(x-1)(y)) count += 1
        if(world(x+1)(y)) count += 1
        if(world(x-1)(y+1)) count += 1
        if(world(x)(y+1)) count += 1
        if(world(x+1)(y+1)) count += 1
      }
      if(x == 0){
        if(world(width-1)(y-1)) count += 1
        if(world(x)(y-1)) count += 1
        if(world(x+1)(y-1)) count += 1
        if(world(width-1)(y)) count += 1
        if(world(x+1)(y)) count += 1
        if(world(width-1)(y+1)) count += 1
        if(world(x)(y+1)) count += 1
        if(world(x+1)(y+1)) count += 1
      }
      if(x == width -1){
        if(world(x-1)(y-1)) count += 1
        if(world(x)(y-1)) count += 1
        if(world(0)(y-1)) count += 1
        if(world(x-1)(y)) count += 1
        if(world(0)(y)) count += 1
        if(world(x-1)(y+1)) count += 1
        if(world(x)(y+1)) count += 1
        if(world(0)(y+1)) count += 1
      }
    }
    if(y == 0){
      if(x != 0 && x != width - 1){
        if(world(x-1)(height-1)) count += 1
        if(world(x)(height-1)) count += 1
        if(world(x+1)(height-1)) count += 1
        if(world(x-1)(y)) count += 1
        if(world(x+1)(y)) count += 1
        if(world(x-1)(y+1)) count += 1
        if(world(x)(y+1)) count += 1
        if(world(x+1)(y+1)) count += 1
      }
      if(x == 0){
        if(world(width-1)(height-1)) count += 1
        if(world(x)(height-1)) count += 1
        if(world(x+1)(height-1)) count += 1
        if(world(width-1)(y)) count += 1
        if(world(x+1)(y)) count += 1
        if(world(width-1)(y+1)) count += 1
        if(world(x)(y+1)) count += 1
        if(world(x+1)(y+1)) count += 1
      }
      if(x == width -1){
        if(world(x-1)(height-1)) count += 1
        if(world(x)(height-1)) count += 1
        if(world(0)(height-1)) count += 1
        if(world(x-1)(y)) count += 1
        if(world(0)(y)) count += 1
        if(world(x-1)(y+1)) count += 1
        if(world(x)(y+1)) count += 1
        if(world(0)(y+1)) count += 1
      }
    }
    if(y == height - 1){
      if(x != 0 && x != width - 1){
        if(world(x-1)(y-1)) count += 1
        if(world(x)(y-1)) count += 1
        if(world(x+1)(y-1)) count += 1
        if(world(x-1)(y)) count += 1
        if(world(x+1)(y)) count += 1
        if(world(x-1)(0)) count += 1
        if(world(x)(0)) count += 1
        if(world(x+1)(0)) count += 1
      }
      if(x == 0){
        if(world(width-1)(y-1)) count += 1
        if(world(x)(y-1)) count += 1
        if(world(x+1)(y-1)) count += 1
        if(world(width-1)(y)) count += 1
        if(world(x+1)(y)) count += 1
        if(world(width-1)(0)) count += 1
        if(world(x)(0)) count += 1
        if(world(x+1)(0)) count += 1
      }
      if(x == width -1){
        if(world(x-1)(y-1)) count += 1
        if(world(x)(y-1)) count += 1
        if(world(0)(y-1)) count += 1
        if(world(x-1)(y)) count += 1
        if(world(0)(y)) count += 1
        if(world(x-1)(0)) count += 1
        if(world(x)(0)) count += 1
        if(world(0)(0)) count += 1
      }
    }

    return count
  }

  def nextGen(): Unit ={
    val t1 = System.nanoTime()
    val nextGeneration = Array.ofDim[Boolean](width, height)
    for(i <- 0 to width -1; j <- 0 to height - 1){

      val n = countneighbours(i, j)
      if(world(i)(j)){
        n match {
          case 0 => nextGeneration(i)(j) = false
          case 1 => nextGeneration(i)(j) = false
          case 2 => nextGeneration(i)(j) = true
          case 3 => nextGeneration(i)(j) = true
          case 4 => nextGeneration(i)(j) = false
          case 5 => nextGeneration(i)(j) = false
          case 6 => nextGeneration(i)(j) = false
          case 7 => nextGeneration(i)(j) = false
          case 8 => nextGeneration(i)(j) = false
        }
      }
      else {
        if(n == 3){nextGeneration(i)(j) = true}
      }
    }
    world = nextGeneration
    timeser = (System.nanoTime() - t1)/1000
  }

  /*def nextGenParallel(): Unit ={
    val t1 = System.nanoTime()
    val nextGeneration = Array.ofDim[Boolean](width, height)

    for(i <- 0 to width -1; j <- 0 to height - 1 by 4){

      mes1.height = height
      mes1.width = width
      mes1.w = world
      mes1.x = i
      mes1.y = (j)

      mes2.height = height
      mes2.width = width
      mes2.w = world
      mes2.x = i
      mes2.y = (j+1)

      mes3.height = height
      mes3.width = width
      mes3.w = world
      mes3.x = i
      mes3.y = (j+2)

      mes4.height = height
      mes4.width = width
      mes4.w = world
      mes4.x = i
      mes4.y = (j+3)

      val f1 = one ? mes1
      val f2 = two ? mes2
      val f3 = three ? mes3
      val f4 = three ? mes4

      val result1 = Await.result(f1, timeout.duration)
      val result2 = Await.result(f2, timeout.duration)
      val result3 = Await.result(f3, timeout.duration)
      val result4 = Await.result(f4, timeout.duration)

      nextGeneration(i)(j) = result1.asInstanceOf[Boolean]
      nextGeneration(i)(j+1) = result2.asInstanceOf[Boolean]
      nextGeneration(i)(j+2) = result3.asInstanceOf[Boolean]
      nextGeneration(i)(j+3) = result4.asInstanceOf[Boolean]


    }
    world = nextGeneration
    timeser = (System.nanoTime() - t1)/1000
  }*/

  def singletrue(): Unit ={
    singlestep = true
  }

  def singlefalse(): Unit ={
    singlestep = false
  }

  def setPoint(x: Int, y: Int): Unit ={
    if(world(x)(y)){
      world(x)(y) = false
    }
    else {
      world(x)(y) = true
    }
  }
}
