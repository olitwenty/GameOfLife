import scala.swing._

/**
  * Created by Oliver on 19.05.2017.
  */
class UI(w: World) extends MainFrame {
  title = "Game of Life"
  visible = true

  val canvas = new Canvas(w)
  val buttonstart = Button("Start") {w.singletrue(); println("start")}
  val buttonstop = Button("Stop") {w.singlefalse(); println("stop")}
  val buttonnextgen = Button("NextGen") {w.nextGen()}
  val buttonrepopulate = Button("Repopulate") {w.populate()}
  val buttonende = Button("Exit") {sys.exit()}
  contents = new BoxPanel(Orientation.Vertical){
    contents += canvas
    contents += new BoxPanel(Orientation.Horizontal){
      contents += buttonstart
      contents += buttonstop}
  }
}

