import akka.actor.Actor

/**
  * Created by Oliver on 24.05.2017.
  */

class NextGenActor extends Actor{
  def receive = {
    case x: actormessage => sender ! nextGen(x)
  }

  def nextGen(x: actormessage): Boolean ={
    val t1 = System.nanoTime()
    var nextworld = false
    val n = countneighbours(x)
    if(x.w(x.x)(x.y)){
      n match {
        case 0 => nextworld = false
        case 1 => nextworld = false
        case 2 => nextworld = true
        case 3 => nextworld = true
        case 4 => nextworld = false
        case 5 => nextworld = false
        case 6 => nextworld = false
        case 7 => nextworld = false
        case 8 => nextworld = false
      }
    }
    else {
      if(n == 3){nextworld = true}
    }
    println("sdgad", (System.nanoTime() - t1))
    return nextworld
  }

  def countneighbours(m: actormessage): Int = {
    val world = m.w
    var count = 0

    if(m.y != 0 && m.y != m.height - 1){
      if(m.x != 0 && m.x != m.width - 1){
        if(world(m.x-1)(m.y-1)) count += 1
        if(world(m.x)(m.y-1)) count += 1
        if(world(m.x+1)(m.y-1)) count += 1
        if(world(m.x-1)(m.y)) count += 1
        if(world(m.x+1)(m.y)) count += 1
        if(world(m.x-1)(m.y+1)) count += 1
        if(world(m.x)(m.y+1)) count += 1
        if(world(m.x+1)(m.y+1)) count += 1
      }
      if(m.x == 0){
        if(world(m.width-1)(m.y-1)) count += 1
        if(world(m.x)(m.y-1)) count += 1
        if(world(m.x+1)(m.y-1)) count += 1
        if(world(m.width-1)(m.y)) count += 1
        if(world(m.x+1)(m.y)) count += 1
        if(world(m.width-1)(m.y+1)) count += 1
        if(world(m.x)(m.y+1)) count += 1
        if(world(m.x+1)(m.y+1)) count += 1
      }
      if(m.x == m.width -1){
        if(world(m.x-1)(m.y-1)) count += 1
        if(world(m.x)(m.y-1)) count += 1
        if(world(0)(m.y-1)) count += 1
        if(world(m.x-1)(m.y)) count += 1
        if(world(0)(m.y)) count += 1
        if(world(m.x-1)(m.y+1)) count += 1
        if(world(m.x)(m.y+1)) count += 1
        if(world(0)(m.y+1)) count += 1
      }
    }
    if(m.y == 0){
      if(m.x != 0 && m.x != m.width - 1){
        if(world(m.x-1)(m.height-1)) count += 1
        if(world(m.x)(m.height-1)) count += 1
        if(world(m.x+1)(m.height-1)) count += 1
        if(world(m.x-1)(m.y)) count += 1
        if(world(m.x+1)(m.y)) count += 1
        if(world(m.x-1)(m.y+1)) count += 1
        if(world(m.x)(m.y+1)) count += 1
        if(world(m.x+1)(m.y+1)) count += 1
      }
      if(m.x == 0){
        if(world(m.width-1)(m.height-1)) count += 1
        if(world(m.x)(m.height-1)) count += 1
        if(world(m.x+1)(m.height-1)) count += 1
        if(world(m.width-1)(m.y)) count += 1
        if(world(m.x+1)(m.y)) count += 1
        if(world(m.width-1)(m.y+1)) count += 1
        if(world(m.x)(m.y+1)) count += 1
        if(world(m.x+1)(m.y+1)) count += 1
      }
      if(m.x == m.width -1){
        if(world(m.x-1)(m.height-1)) count += 1
        if(world(m.x)(m.height-1)) count += 1
        if(world(0)(m.height-1)) count += 1
        if(world(m.x-1)(m.y)) count += 1
        if(world(0)(m.y)) count += 1
        if(world(m.x-1)(m.y+1)) count += 1
        if(world(m.x)(m.y+1)) count += 1
        if(world(0)(m.y+1)) count += 1
      }
    }
    if(m.y == m.height - 1){
      if(m.x != 0 && m.x != m.width - 1){
        if(world(m.x-1)(m.y-1)) count += 1
        if(world(m.x)(m.y-1)) count += 1
        if(world(m.x+1)(m.y-1)) count += 1
        if(world(m.x-1)(m.y)) count += 1
        if(world(m.x+1)(m.y)) count += 1
        if(world(m.x-1)(0)) count += 1
        if(world(m.x)(0)) count += 1
        if(world(m.x+1)(0)) count += 1
      }
      if(m.x == 0){
        if(world(m.width-1)(m.y-1)) count += 1
        if(world(m.x)(m.y-1)) count += 1
        if(world(m.x+1)(m.y-1)) count += 1
        if(world(m.width-1)(m.y)) count += 1
        if(world(m.x+1)(m.y)) count += 1
        if(world(m.width-1)(0)) count += 1
        if(world(m.x)(0)) count += 1
        if(world(m.x+1)(0)) count += 1
      }
      if(m.x == m.width -1){
        if(world(m.x-1)(m.y-1)) count += 1
        if(world(m.x)(m.y-1)) count += 1
        if(world(0)(m.y-1)) count += 1
        if(world(m.x-1)(m.y)) count += 1
        if(world(0)(m.y)) count += 1
        if(world(m.x-1)(0)) count += 1
        if(world(m.x)(0)) count += 1
        if(world(0)(0)) count += 1
      }
    }

    return count
  }
}
