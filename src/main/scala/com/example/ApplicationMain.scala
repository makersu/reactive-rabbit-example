package com.example

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Sink, Source}
import akka.util.ByteString
import io.scalac.amqp.Connection

object ApplicationMain extends App {

  //  // streaming invoices to Accounting Department
  //  val connection = Connection()
  //  // create org.reactivestreams.Publisher
  //  val queue = connection.consume(queue = "invoices")
  //  // create org.reactivestreams.Subscriber
  //  val exchange = connection.publish(exchange = "accounting_department",
  //    routingKey = "invoices")
  //
  //  implicit val system = ActorSystem()
  //  implicit val mat = ActorMaterializer()
  //  // Run akka-streams with queue as Source and exchange as Sink
  //  Source.fromPublisher(queue).map(_.message).runWith(Sink.fromSubscriber(exchange))


  implicit val system = ActorSystem()
  implicit val mat = ActorMaterializer()

  val connection = Connection()

  val queue = connection.consume(queue = "messages")

  //  Source.fromPublisher(queue)
  //    .map(msg => ByteString.fromArray(msg.message.body.toArray).utf8String)
  //    .runWith(Sink.foreach(println))

  val rabbitConsumerActor = system.actorOf(RabbitConsumerActor.props, "RabbitConsumerActor")

  Source.fromPublisher(queue)
    .map(msg => ByteString.fromArray(msg.message.body.toArray).utf8String)
    .runWith(Sink.actorRef(rabbitConsumerActor, () => println("finished")))


}