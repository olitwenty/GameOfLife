import java.awt.{Color, Graphics2D}
import java.awt.geom._

import scala.swing._
import scala.swing.event.{Event, MouseClicked}

/**
  * Created by Oliver on 19.05.2017.
  */
class Canvas(val w: World) extends Component {
  preferredSize = new Dimension(w.cellsize * w.width, w.cellsize * w.height)

  var lastX: Int = 0
  var lasty: Int = 0

  listenTo(mouse.clicks)
  reactions += {
    case MouseClicked(_, p, _, _, _) => Click(p.x, p.y)
  }

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

  def Click(x: Int, y: Int) {
    val cellx = x/w.cellsize
    val celly = y/w.cellsize
    lastX = cellx +1
    lasty = celly +1
    w.setPoint(cellx, celly)
    repaint()
    publish(ClickEvent(lastX, lasty))
  }
}
