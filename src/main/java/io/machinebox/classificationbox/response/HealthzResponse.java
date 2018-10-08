package io.machinebox.classificationbox.response;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class HealthzResponse extends BaseResponse {

    public static class MetaData {
        @SerializedName("boxname")
        private String boxName;
        private String build;

        public MetaData() {

        }

        public String getBoxName() {
            return boxName;
        }

        public String getBuild() {
            return build;
        }

        @Override
        public String toString() {
            return "MetaData{" +
                    "boxName='" + boxName + '\'' +
                    ", build='" + build + '\'' +
                    '}';
        }
    }

    public static class Error {
        private String error;
        private String description;

        public Error() {

        }

        public String getError() {
            return error;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return "Error{" +
                    "error='" + error + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }

    private String hostname;
    @SerializedName("metadata")
    private MetaData metaData;
    private Error[] errors;

    public HealthzResponse() {

    }

    public String getHostname() {
        return hostname;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public Error[] getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "HealthzResponse{" +
                "hostname='" + hostname + '\'' +
                ", metaData=" + metaData +
                ", errors=" + Arrays.toString(errors) +
                '}';
    }
}

