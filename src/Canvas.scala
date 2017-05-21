import java.awt.{Color, Graphics2D}
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

    //horizontal lines
    for(i <- 0 to w.width + 1) {
      g.draw(new Line2D.Double(i * w.cellsize, 0, i * w.cellsize, d.height))
    }

    //vertical lines
    for(i <- 0 to w.height + 1) {
      g.draw(new Line2D.Double(0, i * w.cellsize, d.width, i * w.cellsize))
    }

    g.setColor(Color.BLUE)

    //paint cells
    for(i <- 0 to w.width - 1; j <- 0 to w.height - 1) {
      if (w.world(i)(j)){
        g.fillRect(i * w.cellsize + 1, j * w.cellsize + 1, w.cellsize - 1, w.cellsize - 1)
      }
    }
  }
}
