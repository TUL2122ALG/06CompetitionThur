public enum RacingStatus {
    NOT_RUNNING("NR"), RUNNING("R"), FINISHED("F"), DISQUALIFIED("D");

    private String racingStatus;

    private RacingStatus(String racingStatus) {
        this.racingStatus = racingStatus;
    }

    public String getRacingStatusValue() {
        return racingStatus;
    }

}