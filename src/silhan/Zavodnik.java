
package silhan;

public class Zavodnik {
    private String name;
    private String surname;
    private int year; //rocnik
    private String gender;
    private int idNum;
    private long startTime; //pocet sekund
    private long finishTime; //pocet sekund
    private long totalTime;
    private static int counter=0;
    
    public Zavodnik(String name, String surname, int year, String gender){
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.gender = gender;
        this.counter+=1;
        this.idNum=counter;
        this.startTime=0;
        this.finishTime=0;
        this.totalTime=0;
    }
    //settery
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    public void setStartTime(int hour, int min, int sec){
        this.startTime = TimeTools.timeToSeconds(hour, min, sec);
    }
    public void setStartTime(String time){
        this.startTime = TimeTools.timeToSeconds(time);
    }
    
    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
    }
    public void setFinishTime(int hour, int min, int sec){
        this.finishTime = TimeTools.timeToSeconds(hour, min, sec);
    }
    public void setFinishTime(String time){
        this.finishTime = TimeTools.timeToSeconds(time);
    }
    
    public void setTotalTime(){
        this.totalTime = TimeTools.timeCompare(startTime, finishTime);
    }
    
    //gettery
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getYear() {
        return year;
    }

    public String getGender() {
        return gender;
    }

    public int getIdNum() {
        return idNum;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getFinishTime() {
        return finishTime;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public static int getCounter() {
        return counter;
    }
    
    
    
}
