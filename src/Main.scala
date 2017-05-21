
/**
  * Created by Oliver on 19.05.2017.
  */
object Main {
  def main(args: Array[String]){
    val world = new World
    val ui = new UI(world)
    ui.visible = true
    Thread.sleep(5000)
    world.populate()
    ui.canvas.repaint()
    println("exit")
  }
}
