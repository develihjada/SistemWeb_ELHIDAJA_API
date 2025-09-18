package com.elhidaja.apiselhidaja.configuration.deserializadorEspacios;

import com.elhidaja.apiselhidaja.util.deserializador.EliminarEspaciosInicioFInal;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class JaksonConfig {

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper mapper = new ObjectMapper();

    SimpleModule module = new SimpleModule();
    module.addDeserializer(String.class, new EliminarEspaciosInicioFInal());
    mapper.registerModule(module);
    return mapper;
  }
  /**
   * @PostConstruct
   *                public void setUp() {
   *                SimpleModule module = new SimpleModule();
   *                module.addDeserializer(String.class, new
   *                EliminarEspaciosInicioFInal());
   *                objectMapper.registerModule(module);
   *                }
   * 
   */

}
