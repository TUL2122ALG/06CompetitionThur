/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sikola;

/**
 *
 * @author kubas
 */
public class Competition {
    private Competitor[] competitors;
    private String name;
    private int numOfCompetitors;
    private int timeOfStart;
    private int waveSize;
    private int waveNum;
    private int waveDelay;

    private Competition(String name, int waveDelay, int waveSize) {
        this.name = name;
        this.timeOfStart = 0;
        this.waveDelay = waveDelay;
        this.waveSize = waveSize;
        this.waveNum = 1;
        this.competitors = new Competitor[100];
        this.numOfCompetitors = 0;
    }
    
    public Competition getInstance(String name, int waveDelay, int waveSize){
        return new Competition(name, waveDelay, waveSize);
    }
    
    public void addCompetitior(Competitor newCompetitor){
        this.checkIfWaveFull();
        if(this.numOfCompetitors == competitors.length){
            Competitor[] temp = new Competitor[this.competitors.length + 10];
            System.arraycopy(competitors, 0, temp, 0, competitors.length);
            this.competitors = temp;
        }
        this.competitors[this.numOfCompetitors] = newCompetitor;
        newCompetitor.setRegNum((this.numOfCompetitors +1));
        newCompetitor.seTimeStart(this.timeOfStart);
        this.numOfCompetitors ++;
    }

    private void checkIfWaveFull() {
        if(numOfCompetitors == this.waveSize * this.waveNum){
            this.waveNum ++;
            this.timeOfStart += this.waveDelay;
        }
    }
    
    public int getWinnerIndex(){
        int idx = 0;
        int bestTime = Integer.MAX_VALUE;
        
        for (int i = 0; i < competitors.length; i++) {
            if(competitors[i].getTimeOfRace() < bestTime){
                bestTime = competitors[i].getTimeOfRace();
                idx = i;
            }
            
        }
        return idx;
        
    }
    
    public String getWinnerName(){
        return this.competitors[getWinnerIndex()].getName();
    }
    
    
    
    
}
