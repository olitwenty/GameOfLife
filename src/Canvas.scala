import java.awt.Color
import java.awt.geom._

import scala.swing._

/**
  * Created by Oliver on 19.05.2017.
  */
class Canvas(val w: World) extends Component {
  preferredSize = new Dimension(w.cellsize * w.width, w.cellsize * w.height)

  override def paintComponent(g: Graphics2D): Unit = {
    val d = size
    g.setColor(Color.WHITE)
    g.fillRect(0, 0, d.width, d.height)
    g.setColor(Color.BLACK)
    for(i <- 1 to w.width - 1) {
      g.draw(new Line2D.Double(i * w.cellsize, 0, i * w.cellsize, d.height))
    }
    for(i <- 1 to w.height - 1) {
      g.draw(new Line2D.Double(0, i * w.cellsize, d.width, i * w.cellsize))
    }
  }
}
