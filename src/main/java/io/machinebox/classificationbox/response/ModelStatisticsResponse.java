package io.machinebox.classificationbox.response;

import java.util.List;

public class ModelStatisticsResponse {
    private Integer predictions;
    private Integer examples;
    private List<Clazz> classes;

    public static class Clazz {
        private String name;
        private Integer examples;

        public Clazz() {
        }

        public String getName() {
            return name;
        }

        public Integer getExamples() {
            return examples;
        }
    }

    public ModelStatisticsResponse() {
    }

    public Integer getPredictions() {
        return predictions;
    }

    public Integer getExamples() {
        return examples;
    }

    public List<Clazz> getClasses() {
        return classes;
    }
}
