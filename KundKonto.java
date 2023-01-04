import javax.swing.*;
import java.io.*;

import java.util.Arrays;
import java.util.Scanner;

public class KundKonto {
    private String fornamn, efternamn, adress, email;
    private int alder;
    private char [] losen;

    LoneKonto loneKonto = new LoneKonto();
    SparKonto sparKonto = new SparKonto();






    KundKonto(){


    }

    public String getFornamn() {
        return fornamn;
    }

    public boolean setFornamn(String fornamn) {
        if(fornamn.matches("[a-öA-Ö]+")){
            this.fornamn = fornamn;
            return true;
        }
        else
            return false;
    }


    public String getEfternamn() {
        return efternamn;
    }

    public boolean setEfternamn(String efternamn) {
        if(efternamn.matches("[a-öA-Ö]+")){
            this.efternamn = efternamn;
            return true;
        }
        else
            return false;


    }

    public String getAdress() {
        return adress;
    }

    public boolean setAdress(String adress) {
        if(adress.matches("[a-öA-Ö1-9- ]+")){
            this.adress = adress;
            return true;
        }
        else
            return false;


    }

    public int getAlder() {
        return alder;
    }

    public boolean setAlder(int alder) {
        if(alder > 17 && alder <120) {
            this.alder = alder;
            return true;
        }
        else
            return false;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {   // todo fixa riktig kontroll för setEmail
        this.email = email;
    }

    public char[] getLosen() {
        return losen;
    }

    public boolean setLosen(char[] losen) {
        if(losen.length > 8 && losen.length <12) {
            this.losen = losen;
            return true;
        }
        else
            return false;
    }


    // används för att kontrollera att lösen användaren knappat in stämmer med det som är satt på kontot.
    public boolean losenKontrol(char [] losen){
        if(Arrays.equals(getLosen(),losen) && getLosen() != null){
            return true;
        }
        else return false;
    }

    // Används som popup kontroll av lösen vid tex insättningar
    // den skickar inknappade lösen till losenKontroll.

    public boolean losenPopUp(){
        boolean ok = false;
        char [] a;
        while(!ok) {
            try {
                a = JOptionPane.showInputDialog(null,  "Fyll i ditt lösenord" ).toCharArray();
            } catch (NullPointerException nullPointerException) {
                return false;

            }

            ok = losenKontrol(a);
        }

            return ok;
    }

    //Metod för användaren att byta lösen på sitt konto

    public boolean  bytaLosen() throws IOException {
        boolean ok;
        char [] losen;

        try {
            losen = JOptionPane.showInputDialog(null, "Fyll i ditt lösenord").toCharArray();
        }catch(NullPointerException e){
            return false;
        }

            ok = losenKontrol(losen);

        if(ok){
            try {
                losen = JOptionPane.showInputDialog(null, "Fyll i ditt nya lösen").toCharArray();
            }catch (NullPointerException e){
                return false;
            }
            ok = setLosen(losen);
        }
        if(ok){
            setLosen(losen);
            JOptionPane.showMessageDialog(null,"Lösen är ändrat du kommer nu loggas ut","Konto",JOptionPane.INFORMATION_MESSAGE);
            sparaKonto();
            return true;
        }
        else
            JOptionPane.showMessageDialog(null,"Lösen är inte ändrat försök igen","Konto",JOptionPane.INFORMATION_MESSAGE);
        return false;

    }

    // Metod för att byta email på kontot
    //fungerar inte perfekt då den gamla filen inte blir deleted.
    public boolean bytaEmail() throws IOException{
        boolean ok;


        String email = JOptionPane.showInputDialog(null,  "Fyll i din nya email" );
        ok = losenPopUp();

        if(ok){

            setEmail(email);
            sparaKonto();
             return true;


        }
        return false;

    }

    // hämtar all info om konton och användaren som är inloggad
    // och uppdaterar motsvarande fil med information
    public void sparaKonto()throws IOException {



        var writer = new PrintWriter
                (new BufferedWriter
                        (new FileWriter(getEmail()+".txt")));
        String losen = new String(getLosen());
        writer.println(getEmail());
        writer.println(losen);
        writer.println(getFornamn());
        writer.println(getEfternamn());
        writer.println(getAdress());
        writer.println(getAlder());
        if(loneKonto.getKontoNummer() != null){
        writer.println(loneKonto.getKontoNummer());
        writer.println(loneKonto.getBalans());
        }
        if(sparKonto.getKontoNummer() != null){
        writer.println(sparKonto.getKontoNummer());
        writer.println(sparKonto.getBalans());
        }

        writer.close();


    }
       // används vid inloggning och hämtar all information om användare kontrollera och initiera allt
       //
    public boolean hamtaKonto(String Email, char [] losen)throws  IOException {
        boolean ok;
        char[] kontrolLosen;



       Scanner scan = new Scanner(System.in);
        try {
            scan = new Scanner(new File(Email + ".txt"));
        } catch (FileNotFoundException fileNotFoundException) {
            JOptionPane.showMessageDialog(null, "Finns inget konto kopplat till mailadressen", "Fel",
                    JOptionPane.WARNING_MESSAGE, null);
              return false;
        }


        setEmail(scan.nextLine());
        kontrolLosen = scan.nextLine().toCharArray();
        ok = setLosen(kontrolLosen);
        if (!ok) {
            JOptionPane.showMessageDialog(null, "Något gick fel försök igen, ifall det inte funkar det andra försöket skapa ett nytt konto", "Fel",
                    JOptionPane.WARNING_MESSAGE, null);
        }
        ok = losenKontrol(losen);
        if (!ok) {
            JOptionPane.showMessageDialog(null, "Felaktigt lösen", "Fel",
                    JOptionPane.WARNING_MESSAGE, null);
            return false;
        }
        if (ok) {
            setFornamn(scan.nextLine());
            setEfternamn(scan.nextLine());
            setAdress(scan.nextLine());
            setAlder(Integer.parseInt(scan.nextLine()));
        }
        if(scan.hasNextLine()){
        loneKonto.setKontoNummer(scan.nextLine());
        loneKonto.setBalans(parseDouble(scan.nextLine()));
        }
        if(scan.hasNextLine()){
            sparKonto.setKontoNummer(scan.nextLine());
            sparKonto.setBalans(parseDouble(scan.nextLine()));
        }
        while(scan.hasNextLine()){
            loneKonto.inSattning( parseDouble(scan.nextLine()));
        }
        return true;


    }

     // möjligör för programmet att flytta pengar mellan två användares lönekonton
     // summan dras från avsändaren och det läggs till i motttagarens fil.
    public boolean overForingLoneKonto(double summa,  String emailMottagare )throws IOException {

        if(loneKonto.getBalans() > summa) {




            Scanner scan = new Scanner(System.in);
            try {
                scan = new Scanner(new File(emailMottagare + ".txt"));
            } catch (FileNotFoundException fileNotFoundException) {
                JOptionPane.showMessageDialog(null, "Finns inget konto kopplat till mailadressen", "Fel",
                        JOptionPane.WARNING_MESSAGE, null);
                return false;
            }

            loneKonto.uttag(summa);
            var writer = new PrintWriter
                    (new BufferedWriter
                            (new FileWriter(emailMottagare + ".txt", true)));
            writer.println(summa);
            writer.close();
            return true;
        }
        else
            JOptionPane.showMessageDialog(null, "Balansen på ditt konto är för lågt", "Överföring ej möjligt",
                    JOptionPane.WARNING_MESSAGE, null);
            return false;

    }


     // används återkommande för att validera indata
    public  double parseDouble(String a){
        boolean ok = true;
        double b = 0;
        try{
            b = Double.parseDouble(a);

        }catch (NumberFormatException e){
            ok = false;
        }
        if(ok)
            return b;
        else
            return 0;
    }
}



