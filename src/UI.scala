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
  val buttonnextgen = Button("NextGen") {bnextgen()}
  val buttonrepopulate = Button("Repopulate") {brepopulate()}
  val buttonende = Button("Exit") {sys.exit()}
  val labelser = new Label("Serial: -")
  val labelpar = new Label("Parallel: -")
  contents = new BoxPanel(Orientation.Vertical){
    contents += canvas
    /*contents += new BoxPanel(Orientation.Horizontal){
      contents += labelser
      contents += Swing.HGlue
      contents += labelpar
      contents += Swing.HGlue

      border = Swing.EmptyBorder(10, 10, 10, 10)
    }*/
    contents += new BoxPanel(Orientation.Horizontal){
      contents += buttonstart
      contents += Swing.HGlue
      contents += buttonstop
      contents += Swing.HGlue
      contents += buttonnextgen
      contents += Swing.HGlue
      contents += buttonrepopulate
      contents += Swing.HGlue
      contents += buttonende

      border = Swing.EmptyBorder(10, 10, 10, 10)
    }
  }

  def bnextgen(): Unit ={
    w.nextGen()
    canvas.repaint()
    //labelser.text = "Serial: " + w.timeser + " Mikrosekunden"
  }

  def brepopulate(): Unit ={
    w.populate()
    canvas.repaint()
  }
}

