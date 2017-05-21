import scala.util.Random

/**
  * Created by Oliver on 19.05.2017.
  */
class World {
  val width = 20
  val height = 20
  val cellsize = 30

  val world = Array.ofDim[Boolean](width, height)

  populate()

  def populate(): Unit = {
    val rand = new Random
    for(i <- 0 to width - 1; j <- 0 to height - 1) {
      world(i)(j) = rand.nextBoolean()
    }
  }

  def countneighbours(x: Int, y: Int): Int = {
    var count = 0
    
  }
}
