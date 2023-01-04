import javax.swing.*;

import java.util.Random;

public class  LoneKonto  {
    private String kontoNummer;
    private double balans;




     LoneKonto(){
         Random random = new Random();
         setBalans(100);
         String kontonummer = "";
         int j;
         for(int i = 0; i<8; i++){
              j = random.nextInt(10);
             kontonummer += j;
         }
         setKontoNummer(kontonummer);


    }


    public String getKontoNummer() {
        return kontoNummer;
    }

    public void setKontoNummer(String kontoNummer) {
        this.kontoNummer = kontoNummer;
    }

    public double getBalans() {
        return balans;
    }

    public void setBalans(double balans) {
        this.balans = balans;
    }

    public void uttag(double uttag){
         if(getBalans()> uttag && uttag <5000){
             setBalans(getBalans()-uttag);

         }
         else
             JOptionPane.showMessageDialog(null, "Balansen på ditt konto är för lågt", "Uttag ej möjligt",
                JOptionPane.WARNING_MESSAGE, null);


    }
    /*public void overForingLoneKonto(double summa,String email)throws IOException {
        KundKonto mottagare = new KundKonto();


        mottagare.hamtaKonto(email);            //todo säkra metoden
        mottagare.loneKonto.setBalans(getBalans()+summa);
        mottagare.sparaKonto();

    }
     */





    public boolean inSattning(double inSattning){
         setBalans(getBalans()+ inSattning);
         return true;
    }
}

