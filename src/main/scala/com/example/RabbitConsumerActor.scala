package com.example

import akka.actor.{Props, ActorLogging, Actor}

/**
  * Created by marksu on 4/18/16.
  */

object RabbitConsumerActor {

  val props = Props[RabbitConsumerActor]

}

class RabbitConsumerActor extends Actor with ActorLogging {
  def receive = {
    case x:String => log.info("received: " + x)
    case _ => log.info("received unknown message")
  }
}

