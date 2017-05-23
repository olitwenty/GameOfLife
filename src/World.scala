import scala.util.Random

/**
  * Created by Oliver on 19.05.2017.
  */
class World {
  val width = 100
  val height = 100
  val cellsize = 9
  var singlestep = false

  var world = Array.ofDim[Boolean](width, height)

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
  }

  def singletrue(): Unit ={
    singlestep = true
  }

  def singlefalse(): Unit ={
    singlestep = false
  }
}
