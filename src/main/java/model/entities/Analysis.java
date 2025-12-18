package model.entities;

import com.fasterxml.jackson.databind.JsonNode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Analysis {

    private final Integer id;
    private final String algorithm;
    private final String algoCase;
    private final JsonNode jsonFile;
    private final LocalDateTime date;
    private final String user;
    private final BigDecimal size;

    private Analysis(Builder builder) {
        id = builder.id;
        algorithm = builder.algorithm;
        algoCase = builder.algoCase;
        jsonFile = builder.jsonFile;
        date = builder.date;
        user = builder.user;
        size = builder.size;
    }

    public Integer getId() {
        return id;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getAlgoCase() {
        return algoCase;
    }

    public JsonNode getJsonFile() {
        return jsonFile;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getUser() {
        return user;
    }

    public BigDecimal getSize() {
        return size;
    }

    public static final class Builder {
        private final Integer id;
        private final String algorithm;
        private final String algoCase;
        private final JsonNode jsonFile;
        private final LocalDateTime date;
        private final String user;
        private final BigDecimal size;

        public Builder(Integer id, String algorithm, String algoCase, JsonNode jsonFile,
                       LocalDateTime date, String user, BigDecimal size) {
            this.id = id;
            this.algorithm = algorithm;
            this.algoCase = algoCase;
            this.jsonFile = jsonFile;
            this.date = date;
            this.user = user;
            this.size = size;
        }

        public Analysis build() {
            return new Analysis(this);
        }
    }
}
