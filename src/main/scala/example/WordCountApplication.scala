package example

import java.time.Duration
import java.util.Properties
import java.util.concurrent.TimeUnit

import org.apache.kafka.streams.kstream.Consumed
import org.apache.kafka.streams.kstream.Materialized
import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.common.serialization.Serdes._
import org.apache.kafka.common.serialization._
import org.apache.kafka.streams.scala._
import org.apache.kafka.streams.scala.kstream._
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}

object WordCountApplication extends App {
  val props: Properties = {
    val p = new Properties()
    p.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-application")
    p.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka-1:9091")
    p
  }

  val builder: StreamsBuilder = new StreamsBuilder
  val textLines: KStream[String, String] = builder.stream[String, String]("TextLinesTopic")//(ImplicitConversions.consumedFromSerde[String, String])
//  val wordCounts: KTable[String, Long] = textLines
//    .flatMapValues(textLine => textLine.toLowerCase.split("\\W+"))
//    .groupBy((_, word) => word)
//    .count()(Materialized.as("counts-store"))
//  wordCounts.toStream.to("WordsWithCountsTopic")
//
//  val streams: KafkaStreams = new KafkaStreams(builder.build(), props)
//  streams.start()

//  sys.ShutdownHookThread {
//    streams.close(Duration.ofSeconds(10))
//  }
}