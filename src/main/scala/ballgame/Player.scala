package ballgame

import akka.actor.{Actor, ActorRef}

import scala.util.Random

class Player(val id: Int, val allPlayers: Array[ActorRef]) extends Actor{

  override def receive: PartialFunction[Any, Unit] = {
    case Ball(count) =>
      val nextPlayer: Int = getRandomPlayer
      val newCount = count + 1
      println(s"Count: $newCount: Player $id pass ball to player $nextPlayer")

      Thread.sleep(300)

      allPlayers(nextPlayer) ! Ball(newCount)

    case _ => println("Cannot parse message")
  }

  private def getRandomPlayer: Int = {
    val possibleIndexes = allPlayers.zipWithIndex.map(_._2).filter(_ != id)
    possibleIndexes(Random.nextInt(possibleIndexes.length))
  }
}
