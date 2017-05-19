import scala.swing._

/**
  * Created by Oliver on 19.05.2017.
  */
class UI extends MainFrame {
  title = "Game of Life"
  contents = new Canvas(new World)
}
