public enum TaskStatus {
    NEW,
    IN_PROGRESS,
    DONE;

    public static TaskStatus parse(String value) {
        for (TaskStatus status : values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + value);
    }
}
