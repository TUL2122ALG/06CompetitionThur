public enum Gender {

    MALE("M"), FEMALE("F"), OTHER("O");

    private String gender;
    private char genderShortcut;

    private Gender(String gender) {
        this.gender = gender;
    }

    public String getGenderValue() {
        return gender;
    }

    public static Gender of(String gender) {
        for (Gender g : Gender.values()) {
            if (g.getGenderValue().equals(gender)) {
                return g;
            }
        }
        return null;

    }
}
