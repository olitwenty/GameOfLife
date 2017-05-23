
/**
  * Created by Oliver on 19.05.2017.
  */
object Main {
  def main(args: Array[String]){
    val world = new World
    val ui = new UI(world)

    while(true){
      Thread.sleep(100)
      if(world.singlestep){
        allgenerations()
      }
    }

    def allgenerations(): Unit ={
      while(world.singlestep) {
        world.nextGen()
        ui.canvas.repaint()
        Thread.sleep(1000)
      }
    }
  }
}
