package io.confluent.developer.spring.avro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import io.confluent.developer.Persona;
import lombok.extern.apachecommons.CommonsLog;

@Service
@CommonsLog(topic = "Producer Logger")
public class Producer {

  @Value("${topic.name}")
  private String TOPIC;

  private final KafkaTemplate<String, Persona> kafkaTemplate;

  @Autowired
  public Producer(KafkaTemplate<String, Persona> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  void sendMessage(Persona persona) {
    this.kafkaTemplate.send(this.TOPIC, persona.getNombre(), persona);
    log.info(String.format("Produced user -> %s", persona));
  }
}
