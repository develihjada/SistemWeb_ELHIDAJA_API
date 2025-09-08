package com.hidajasac.hidajasac_apis.util.deserializadores;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class EliminarEspaciosInicioFInal extends JsonDeserializer<String>{
    /**
     * funcion para desserializar textos elimina los espaicos en blanvco al inicio y al final
     * de cada String y si tiene mas de dos espacios entre las palabras las sustituye
     * a uno
     * @param parser
     * @param context
     * @return
     */
    @Override
    public String deserialize(JsonParser parser, DeserializationContext context) {
        try {
            String value = parser.getText();
            if (value == null) {
                return null;
            }
            return value.trim().replaceAll("\\s{2,}", " ");
        } catch (Exception e) {
            throw new RuntimeException("Error al deserializar String ", e);
        }
    }
}
