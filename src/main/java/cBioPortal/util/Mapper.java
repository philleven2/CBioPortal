package cBioPortal.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

public enum Mapper {

  INSTANCE;

  private final ObjectMapper mapper = new ObjectMapper();

  private Mapper() {

    // Configure ObjectMapper
    mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

    // Register mapper with Afterburner
    // Boosts serialization / deserialization speed by 30-40%
    // Eliminates reflection by generating bytecode that does direct access tofields and calls to
    // getters/setters
    mapper.registerModule(new AfterburnerModule());

  }

  public ObjectMapper getObjectMapper() {

    return mapper;

  }

}
