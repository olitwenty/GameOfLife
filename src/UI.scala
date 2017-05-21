import scala.swing._

/**
  * Created by Oliver on 19.05.2017.
  */
class UI(w: World) extends MainFrame {
  title = "Game of Life"
  val canvas = new Canvas(w)
  contents = canvas
}
