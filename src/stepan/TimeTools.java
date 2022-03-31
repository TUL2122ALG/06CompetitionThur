/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stepan;

/**
 *
 * @author mates
 */
public class TimeTools {
    
    public static long PrevodNaSekundy(int hodiny, int minuty, int sekundy){
        return (hodiny*60*60) + (minuty*60) + sekundy;
    }
    
    public static String PrevodNaCas(long sekundy){
        int h,m,s;
        h = (int)sekundy/60/60;
        m = (int)sekundy/60%60;
        s = (int)sekundy%60;
        
        return String.format("%02d:%02d:%02d",h,m,s);
    }
    public static void main(String[] args) {
        System.out.println(PrevodNaCas(23342));
    }
}
