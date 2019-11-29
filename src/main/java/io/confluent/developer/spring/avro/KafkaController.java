package io.confluent.developer.spring.avro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.confluent.developer.Persona;

@RestController
@RequestMapping(value = "/persona")
public class KafkaController {

  private final Producer producer;

  @Autowired
  KafkaController(Producer producer) {
    this.producer = producer;
  }

  @PostMapping(value = "/publish")
  public void sendMessageToKafkaTopic(@RequestParam("nombre") String nombre,@RequestParam("apellido") String apellido, @RequestParam("edad") int edad,@RequestParam("correo") String correo) {
    this.producer.sendMessage(new Persona(nombre, apellido));
  }
}