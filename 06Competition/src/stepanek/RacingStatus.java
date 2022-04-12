public enum RacingStatus {
    NOT_RUNNING("NR"), RUNNING("RE"), FINISHED("F"), DISQUALIFIED("D"), REGISTRATED("RE");

    private String racingStatus;

    private RacingStatus(String racingStatus) {
        this.racingStatus = racingStatus;
    }

    public String getRacingStatusValue() {
        return racingStatus;
    }

}