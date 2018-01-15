package ballgame

import akka.actor.{ActorRef, ActorSystem, Props}

object Launcher {
  def main(args: Array[String]): Unit ={
    val playersLimit = 3

    val players: Array[ActorRef] = new Array(playersLimit)
    val actorSystem: ActorSystem = ActorSystem("BallGame")

    for (i <- 0 until playersLimit) {
      val player = actorSystem.actorOf(Props(classOf[Player], i, players))
      players(i) = player
    }

    players(0) ! Ball(0)

  }
}
