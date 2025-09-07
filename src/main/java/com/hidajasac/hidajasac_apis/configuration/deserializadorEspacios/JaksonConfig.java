package com.hidajasac.hidajasac_apis.configuration.deserializadorEspacios;
import com.hidajasac.hidajasac_apis.util.deserializadores.EliminarEspaciosInicioFInal;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration

public class JaksonConfig {
    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void setUp() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(String.class, new EliminarEspaciosInicioFInal());
        objectMapper.registerModule(module);
    }
}
