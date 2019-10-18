package lab

import com.typesafe.config.{Config, ConfigFactory, ConfigObject, ConfigValue}

import scala.collection.JavaConverters._
import java.util
import java.util.Map.Entry

case class KafkaConfig(config: Config)  {
  lazy val value : util.Map[String, Object] = {
    val list : Iterable[ConfigObject] = config.getObjectList("kafka").asScala
      (for {
        item : ConfigObject <- list
        entry : Entry[String, ConfigValue] <- item.entrySet().asScala
        key = entry.getKey
        value = (entry.getValue.unwrapped().toString)
      } yield (key, value: Object)).toMap.asJava
  }
}
