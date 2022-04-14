package silhan;

import java.util.Scanner;

public class Zavod {
    
    private Scanner sc =new Scanner(System.in);
    
    //data
    private String name;
    private Zavodnik[] competitors;
    private int nCompetitors = 0;
    
    //konstruktor
    public Zavod(String name){
        this.name = name;
        this.competitors = new Zavodnik[10];
    }
    
    //gettery
    public String getName() {
        return name;
    }

    public Zavodnik[] getCompetitors() {
        return competitors;
    }

    public int getnCompetitors() {
        return nCompetitors;
    }
    
    //methods
    public void addCompetitors(int number){
        String name; String surname; int year; String gender;
        int prevComp = nCompetitors;
        for(int i =prevComp;i<number;i++){
            if (i==competitors.length){
                extendArray();
            }
            System.out.println("Jmeno:");
            name = sc.next();
            System.out.println("Prijmeni");
            surname = sc.next();
            System.out.println("Rocnik");
            year = sc.nextInt();
            System.out.println("Pohlavi");
            gender = sc.next();
            this.competitors[i]= Zavodnik.getInstance(name, surname, year, gender);
            nCompetitors++;
        }
    }
    
    public void addCompetitor(String name, String surname, int year, String gender){
        this.competitors[nCompetitors]=Zavodnik.getInstance(name, surname, year, gender);
        nCompetitors++;
    }

    public void displayComp(){
        for (int i=0;i<nCompetitors;i++){
            System.out.println(competitors[i]);
        }
    }   

    private void extendArray() {
        Zavodnik[] longer = new Zavodnik[competitors.length+10];
        System.arraycopy(competitors, 0,longer, 0, competitors.length);
        competitors = longer;
    }
    
    public void findFastest(){
        long fastestTime = Integer.MIN_VALUE; int fastest;
        for(int i =0;i<nCompetitors;i++){
            if(competitors[i].getTotalTime()<fastestTime){
                fastestTime= competitors[i].getTotalTime();
                fastest = i;
            }
        }
    }
    //sort?
    
}