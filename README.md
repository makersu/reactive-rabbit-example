reactive-rabbit-example
=========================

A minimal example for an Akka Stream and reactive-rabbit with Scala build 

## build.sbt
```
...
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.4",
  "com.typesafe.akka" %% "akka-stream" % "2.4.4",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test")

libraryDependencies += "io.scalac" %% "reactive-rabbit" % "1.1.0"
...
```

## producer with actor(TODO)

## consume with actor
```
...
  val queue = connection.consume(queue = "messages")

  val rabbitConsumerActor = system.actorOf(RabbitConsumerActor.props, "RabbitConsumerActor")

  Source.fromPublisher(queue)
    .map(msg => ByteString.fromArray(msg.message.body.toArray).utf8String)
    .runWith(Sink.actorRef(rabbitConsumerActor, () => println("finished")))
...
```

## Run
```
git clone https://github.com/makersu/reactive-rabbit-example.git
sbt run
```
