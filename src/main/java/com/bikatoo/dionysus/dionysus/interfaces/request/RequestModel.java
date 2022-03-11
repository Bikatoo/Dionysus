package com.bikatoo.dionysus.dionysus.interfaces.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestModel {

    private String terminal;
    private String uuid;
    private JsonNode extra;
}
