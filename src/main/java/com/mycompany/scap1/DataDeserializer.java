/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.scap1;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;

public class DataDeserializer extends JsonDeserializer<Data> {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Data deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = parser.getCodec().readTree(parser);
        Data data = new Data();
        data.status = node.get("status").asInt();
        data.type = node.get("type").asInt();

        // Deserializar `value` según el valor de `type`
        if (data.type == 0) {
            // Deserializar `value` como `ValueObject`
            data.value = mapper.treeToValue(node.get("value"), ValueObject.class);
        } else if (data.type == 1) {
            // Deserializar `value` como `List<ValueList>`
            data.value = mapper.convertValue(
                node.get("value"),
                mapper.getTypeFactory().constructCollectionType(List.class, ValueList.class)
            );
        }

        return data;
    }
}
