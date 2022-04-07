package susmilich;

/**
 *
 * @author michal.susmilich
 */
public class Zavodnik {

    private String fullName;
    private String timeStart;
    private String timeEnd;
    private String fullTime;
    private int registerNum;
    private String sex;
    private int wave;

    @Override
    public String toString() {
        return "Zavodnik{" + "S registračním číslem: " + registerNum + ", jménem: "+fullName+", pohlavím: " + sex + ", ve vlně: " + wave +", startovacím časem: "+ timeStart+ ", časem konce: "+timeEnd+", v celkovém čase: "+fullTime+'}';
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getFullTime() {
        return fullTime;
    }

    public void setFullTime(String fullTime) {
        this.fullTime = fullTime;
    }

    public int getRegisterNum() {
        return registerNum;
    }

    public void setRegisterNum(int registerNum) {
        this.registerNum = registerNum;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getWave() {
        return wave;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }

    public Zavodnik(String fullName, int registerNum, String sex, int wave) {
        this.fullName = fullName;
        this.registerNum = registerNum;
        this.sex = sex;
        this.wave = wave;
    }
    

}
