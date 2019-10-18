package lab

import java.net.URL
import java.time.Duration
import java.util
import java.util.{Date, Map, Properties}
import java.util.Map.Entry

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

import scala.util.Random
import com.typesafe.config._

import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.util.Properties
import scala.util.{Failure, Success, Try}
import org.apache.kafka.clients.producer.ProducerRecord

import org.apache.kafka.streams.scala.Serdes._
import org.apache.kafka.streams.scala.ImplicitConversions._

object Producer extends App {



  val global_events = Array("maintenance_begin", "maintenance_end", "plan_removed", "plan_added", "sale_begin", "sale_end")
  val eventsCount = 100000
  val rnd = new Random()

  val config: Config = ConfigFactory.load()
  val kafkaConfig = KafkaConfig(config)

  val producer = new KafkaProducer[String, String](kafkaConfig.value)
  val t = System.currentTimeMillis()
  for (i <- Range(0, eventsCount)) {
    val runtime = new Date().getTime()
    val ip = "192.168.2." + rnd.nextInt(255)
    val msg = runtime + "," + i + ",www.example.com," + ip
    val data = new ProducerRecord[String, String]("user-events", "user_id_" + i, msg)
    producer.send(data)


    if (i % 100 == 0) {
      val eventIndex = (Math.random * global_events.length).asInstanceOf[Int]
      val event = global_events(eventIndex) + "_" + System.nanoTime
      val data = new ProducerRecord[String, String]("global-events", event)
      producer.send(data)
    }

    producer.flush
  }

  System.out.println("sent per second: " + eventsCount * 1000 / (System.currentTimeMillis() - t))
  producer.close()

}