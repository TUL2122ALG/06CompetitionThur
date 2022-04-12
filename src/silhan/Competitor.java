
package silhan;

import java.time.LocalDate;

public class Competitor implements Comparable<Competitor>{
    private String name;
    private String surname;
    private int year; //rocnik
    private char gender;
    private int regNum;
    private int startTime; //pocet sekund
    private int finishTime; //pocet sekund
    private int totalTime;
    private static int counter=0;
    //private String competitorCondition; //pouzit enum, neni potreba mit v promenne jen vracet metodou
    private String club;
    
    public Competitor(String name, String surname, int year, char gender, int regNum){
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.gender = gender;
        //this.counter+=1;
        this.regNum=regNum;
        this.startTime=0;
        this.finishTime=0;
        this.totalTime=0;
    }
    
    //kopie zavodnika | metoda clone()
    public Competitor(Competitor c){
        this.name = c.name;
        this.surname = c.surname;
        this.year = c.year;
        this.gender = c.gender;
        this.club = c.club;
        this.regNum = c.regNum;
        this.startTime=c.startTime;
        this.finishTime=c.finishTime;
        this.totalTime=c.getTotalTime();
    }
    
    //factory method
    public static Competitor getInstance(String name, String surname, int year, char gender, int regNum){
        return new Competitor(name,surname,year,gender,regNum);
    }
    //settery
    public void setStartTime(int startTime) { //9*3600 + 12*60
        this.startTime = startTime;
    }
    public void setStartTime(int hour, int min, int sec){ //9 12 0
        this.startTime = TimeTools.timeToSeconds(hour, min, sec);
    }
    public void setStartTime(String time){ //09:12:00
        this.startTime = TimeTools.timeToSeconds(time);
    }
    
    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
        setTotalTime();
    }
    public void setFinishTime(int hour, int min, int sec){
        this.finishTime = TimeTools.timeToSeconds(hour, min, sec);
        setTotalTime();
    }
    public void setFinishTime(String time){
        this.finishTime = TimeTools.timeToSeconds(time);
        setTotalTime();
    }
    
    public void setTotalTime(){ //cas chceme pocitat jen pokud zavodnik dokoncil
        if(getCompetitorCondition() == CompetitorCondition.FINISHED){
            this.totalTime = TimeTools.timeCompare(startTime, finishTime);
        }
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

    public char getGender() {
        return gender;
    }

    public int getRegNum() {
        return regNum;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public static int getCounter() {
        return counter;
    }
    
    public CompetitorCondition getCompetitorCondition() {
        if(this.startTime==0){
            return CompetitorCondition.NOTSTARTED;
        } else if(this.startTime!=0 && this.finishTime==0){
            return CompetitorCondition.STARTED;
        } else {
            return CompetitorCondition.FINISHED;
        }
    }

    public String getClub() {
        return club;
    }
    
    public int getAge(){
        LocalDate current_date = LocalDate.now(); //dnesni datum
        int year = current_date.getYear();
        return year - this.year;
    }
    
    //toString
    @Override
    public String toString() {
        return String.format("%5d %10s %10s %5d %1s %10s %10s %10s",
                this.regNum,this.name, this.surname, this.getAge(), this.gender,
                TimeTools.secondsToTime(this.startTime), TimeTools.secondsToTime(this.finishTime), TimeTools.secondsToTime(this.getTotalTime()));
    }

    /*testovani
    public static void main(String[] args) {
        Competitor c = new Competitor("Jakub", "Silhan",2002,"M");
        System.out.println(c);
        c.setStartTime(9,0,0);
        System.out.println(c);
        c.setFinishTime("10:02:05");
        System.out.println(c);
    }
    */

    @Override
    public int compareTo(Competitor o) {
        /*
        if(this.getTime() < o.getTime(){
            return -1;
        }else if(this.getTime() > o.getTime()){
            return 1;
        }else{
            return 0;
        }
        */
        return this.getTotalTime() - o.getTotalTime();
    }
    
    
}
