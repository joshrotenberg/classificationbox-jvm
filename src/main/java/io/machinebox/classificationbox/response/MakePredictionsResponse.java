package io.machinebox.classificationbox.response;

import java.util.List;

public class MakePredictionsResponse extends BaseResponse {
    private List<Clazz> classes;

    public static class Clazz {
        private String id;
        private Float score;

        public Clazz() {
        }

        public String getId() {
            return id;
        }

        public Float getScore() {
            return score;
        }
    }

    public MakePredictionsResponse() {
    }

    public List<Clazz> getClasses() {
        return classes;
    }
}
