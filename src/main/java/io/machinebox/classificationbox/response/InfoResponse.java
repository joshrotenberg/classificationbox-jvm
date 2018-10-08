package io.machinebox.classificationbox.response;

public class InfoResponse extends BaseResponse {
    public enum Status {
        STARTING("starting..."),
        READY("ready"),
        SHUTTING_DOWN("shutting down");

        private String status;

        Status(String status) {
            this.status = status;
        }

        public String getString() {
            return this.status;
        }

        public static Status fromString(String text) {
            for (Status b : Status.values()) {
                if (b.status.equalsIgnoreCase(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    private String name;
    private Number version;
    private String build;
    private String status;
    private String plan;

    public InfoResponse() {

    }

    public String getName() {
        return name;
    }

    public Number getVersion() {
        return version;
    }

    public String getBuild() {
        return build;
    }

    public Status getStatus() {
        return Status.fromString(this.status);
    }

    public String getPlan() {
        return plan;
    }

    @Override
    public String toString() {
        return "InfoResponse{" +
                "name='" + name + '\'' +
                ", version=" + version +
                ", build='" + build + '\'' +
                ", status='" + status + '\'' +
                ", plan='" + plan + '\'' +
                '}';
    }
}
