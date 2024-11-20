/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.scap1;

/**
 *
 * @author Juan Carlos Cabrera
 */
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.List;
import java.util.Map;

public class Response {
    public boolean success;
    public List<Data> data;
    public List<Object> error;
}

class Data {
    public int status;
    public int type;
     @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
    @JsonSubTypes({
        @JsonSubTypes.Type(value = ValueObject.class, name = "0"),
        @JsonSubTypes.Type(value = ValueList.class, name = "1")
    })
    public Object value;
}

class ValueObject {
    public String url;
    public String query;
    public Object data;
}

class ValueList {
    public String place;
    public String parameter;
    public int ptype;
    public String prefix;
    public String suffix;
    public List<Integer> clause;
    public List<Object> notes;
    public Map<String, DataEntry> data;
    public Conf conf;
    public String dbms;
    
    @JsonProperty("dbms_version")
    public List<String> dbmsVersion;
    public String os;
}

class DataEntry {
    public String title;
    public String payload;
    public int where;
    public Object vector;
    public String comment;
    public Object templatePayload;
    public double matchRatio;
    public Integer trueCode;
    public Integer falseCode;
}

class Conf {
    public Object textOnly;
    public Object titles;
    public Object code;
    public String string;
    public Object notString;
    public Object regexp;
    public Object optimize;
}

