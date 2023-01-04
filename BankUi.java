import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;



public class BankUi extends KundKonto implements ActionListener {

    JFrame frame = new JFrame();         JTextField text1;
    JLabel label1;                       JTextField text2;
    JLabel label2;                       JTextField text3;
                                         JTextField text4;
                                         JTextField text5;
    JLabel label3;                       JTextField text6;                   JSlider slider1;
    JLabel label4;
    JLabel label5;
    JLabel label6;
    JLabel label7;
    JLabel label8;
    JLabel label9;
    JLabel label10;
    JLabel label11;
    JButton button1;    //loginSida
    JButton button2;    //loginSida
    JButton button3;    //startSida
    JButton button4;    //startSida
    JButton button5;    //startSida
    JButton button6;    //kontoSida
    JButton button7;    //kontoSida
    JButton button8;    //kontoSida
    JButton button9;    //kontoSida
    JButton button10;   //kontosida
    JButton button11;  //sparKontosida
    JButton button12;  //sparKontoSida
    JButton button13;  //kontosida
    JButton button14;  //kontosida
    JButton button15;  //kontosida
    JButton button16;  //kontosida
    JButton button17;  // kontosida
    JButton button18;  //kontosida
    JButton button19; //StartSida
    JButton button20; //StartSida
    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JTextField loginEmail;
    JPasswordField loginPassword;


    BankUi() {

       // panel
        panel1 = new JPanel();
        panel1.setSize(600,600);
        panel1.setLayout(null);






        // label
         label1 = new JLabel();
         label2 = new JLabel();
         label3 = new JLabel();
         label1.setBounds(265, 140, 200, 20);
         label2.setBounds(135, 175,100,20);
         label3.setBounds(135,200,100,20);
         label1.setText("Välkommen");
         label2.setText("Email");
         label3.setText("Lösenord");
        //button

        button1 = new JButton("Logga in");
        button1.setFocusable(false);
        button1.addActionListener(this);
        button1.setBounds(250, 225, 100, 20);

        button2 = new JButton("Nytt konto");
        button2.addActionListener(this);
        button2.setBounds(250, 250, 100, 20);

        // login ruta
         loginPassword = new JPasswordField(20);
         loginEmail = new JTextField(20);
        loginPassword.setBounds(200, 200, 200, 20);
        loginEmail.setBounds(200, 175, 200, 20);

        // sätter ihop komponenter login sida
          panel1.add(label1); panel1.add(button1);panel1.add(button2);panel1.add(loginEmail); panel1.add(loginPassword);
          panel1.add(label2); panel1.add(label3);
          panel1.setBackground(Color.lightGray);
          panel1.setVisible(true);
          frame.add(panel1);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(600, 600);
        frame.setResizable(false);
        frame.setTitle("Bank");
        frame.setLocationRelativeTo(null);


        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button2) {    // kallar på nyttKonto

            new nyttKonto();

        }
        if (e.getSource() == button1){    // Försöker logga in
             boolean ok = false;          //om lyckat skapar startSida



            try {

               ok = hamtaKonto(loginEmail.getText(),loginPassword.getPassword());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if(ok){
                panel1.setVisible(false);
                startSida();

            }
        }
        if(e.getSource() == button3){    //skapar kontoSida
            panel2.setVisible(false);
            kontoSida();
        }
        if(e.getSource() == button4) { // skapar sparKontosida
            panel2.setVisible(false);
            sparSida();
        }
        if(e.getSource() == button5){  //loggar ut och skapar inloggningsSida
            boolean ok = true;
            panel2.setVisible(false);
            try{
                sparaKonto();
            }catch (IOException c){
                ok = false;
            }
            if(ok) {
                panel2.setVisible(false);
                loggaUt();
            }
        }
        if(e.getSource() == button6){    //Tillbaka knapp kontoSida
            panel2.setVisible(false);    //stänger ner kontoSida och öppnar startSida
            startSida();
        }
        if(e.getSource() == button7){    // knapp insättning lönekonto / Kontosida
            label10.setVisible(false);
            label11.setVisible(false);
            text6.setVisible(false);
            button18.setVisible(false);

            button10.setVisible(false);  //
            text3.setVisible(false);
            label5.setVisible(false);
            label4.setVisible(true);
            text3.setVisible(true);
            button9.setVisible(true);
        }

        if(e.getSource() == button8) {  // knapp uttag lönekonto/ kontoSida
            label10.setVisible(false);
            label11.setVisible(false);
            text6.setVisible(false);
            button18.setVisible(false);

            boolean ok = false;
            button9.setVisible(false);
            text3.setVisible(false);
            label4.setVisible(false);
            char [] a;

            while(!ok) {
               try{
                    a = JOptionPane.showInputDialog(null, "Fyll i ditt lösenord").toCharArray();
               }catch (NullPointerException nullPointerException){
                   break;
               }
                ok = losenKontrol(a);
                if (ok) {
                    text3.setVisible(true);
                    button10.setVisible(true);
                    label5.setVisible(true);
                }
            }

        }
        if(e.getSource() == button10) {                 //knapp ok som genomför uttag lönekonto / kontosida
                                                        // hör ihopp med button8

          double  uttag = parseDouble(text3.getText());

            if (uttag > 0) {
                 loneKonto.uttag(uttag);

                   text3.setVisible(false);
                   button9.setVisible(false);
                   panel2.setVisible(false);
                   kontoSida();

            }
            else {
                JOptionPane.showMessageDialog(null, "Felaktig inmattning", "Fel",
                        JOptionPane.WARNING_MESSAGE, null);

            }






        }



        if(e.getSource() == button9){                          // knapp ok insättning lönekonto / kontosida
            double insattning = parseDouble(text3.getText());  // hör ihop med button7

            if(insattning > 0){
                loneKonto.inSattning(insattning);
                label4.setVisible(false);
                text3.setVisible(false);
                button9.setVisible(false);
                panel2.setVisible(false);
                try {
                    sparaKonto();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                kontoSida();
            }
            else  JOptionPane.showMessageDialog(null, "Felaktig inmattning", "Fel",
                    JOptionPane.WARNING_MESSAGE, null);
        }

        if(e.getSource() == button11){    // tillbaka knapp sparKonto sida
            panel2.setVisible(false);
            startSida();
        }
        if(e.getSource() == button13){  // insättnings knapp sparkonto / kontoSida
            label10.setVisible(false);
            label11.setVisible(false);
            text6.setVisible(false);
            button18.setVisible(false);

            label4.setVisible(true);
            label5.setVisible(false);
            text3.setVisible(true);
            button15.setVisible(true);

        }
        if(e.getSource() == button14){  // uttag knapp sparkonto / kontosida
            label10.setVisible(false);
            label11.setVisible(false);
            text6.setVisible(false);
            button18.setVisible(false);

            boolean ok = false;
            while(!ok) {
                ok = losenPopUp();
            }
            if(ok) {
                label4.setVisible(false);
                label5.setVisible(true);
                text3.setVisible(true);
                button16.setVisible(true);
                button15.setVisible(false);
            }
        }
        if(e.getSource() == button15){                            //ok knap insättning lönekonto hör ihop med button 13
          double insattning =  parseDouble(text3.getText());

          if(insattning > 0){
              sparKonto.inSattning(insattning);
            label5.setVisible(false);
            text3.setVisible(false);
            button15.setVisible(false);
            panel2.setVisible(false);
            kontoSida();

          }
          else
              JOptionPane.showMessageDialog(null, "Felaktig inmattning", "Fel",
                      JOptionPane.WARNING_MESSAGE, null);
        }
        if(e.getSource() == button16){                     // ok knapp uttag sparkonto / kontosida
           double uttag = parseDouble(text3.getText());    // hör ihop med button14
           if(uttag > 0) {
               sparKonto.uttag(uttag);
               try {
                   sparaKonto();
               } catch (IOException ex) {
                   ex.printStackTrace();
               }
               label5.setVisible(false);
               text3.setVisible(false);
               button16.setVisible(false);
               panel2.setVisible(false);
               kontoSida();
           }
           else
               JOptionPane.showMessageDialog(null, "Felaktig inmattning", "Fel",
                       JOptionPane.WARNING_MESSAGE, null);
        }

        if(e.getSource() == button17){            // Överförings knapp kontoSida
            boolean ok = false;
            while(!ok) {
                ok = losenPopUp();
            }
            if(ok) {
                label4.setVisible(false);
                label5.setVisible(false);
                label10.setVisible(true);
                label11.setVisible(true);
                button15.setVisible(false);
                button16.setVisible(false);

                text3.setVisible(true);
                text6.setVisible(true);
                button18.setVisible(true);
            }
        }
        if(e.getSource() == button18){                   // ok knapp Överföring / kontosida
            boolean ok = true;                           // hör ihop med button 17
            double summa = parseDouble(text3.getText());
            if(summa > 0 ){

                try {
                   ok = overForingLoneKonto(summa, text6.getText());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if(ok){
                       JOptionPane.showMessageDialog(null,"Överföring klar");
                    label4.setVisible(false);
                    text3.setVisible(false);
                    panel2.setVisible(false);
                    kontoSida();

                   }
            }
            else  JOptionPane.showMessageDialog(null,"Felaktig inmatning");
        }

        if(e.getSource() == button19){    // Ändra Lösen Knapp / startSida
            boolean ok = false;
            try {
                ok = bytaLosen();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if(ok){
                panel2.setVisible(false);
                loggaUt();
            }
            if(!ok){
                panel2.setVisible(false);
                button19.setVisible(false);
                startSida();
            }
        }

        if(e.getSource() == button20){
            boolean ok;
            try {
                ok = bytaEmail();
            } catch (IOException ex) {
                ok = false;
                ex.printStackTrace();
            }
            if(ok) {
                JOptionPane.showMessageDialog(null, "Email ändrar du kommer nu loggas ut", "Konto", JOptionPane.INFORMATION_MESSAGE);
                panel2.setVisible(false);
                loggaUt();
            }
            else
                JOptionPane.showMessageDialog(null, "Email gick inte att ändra", "Konto", JOptionPane.INFORMATION_MESSAGE);

        }





    }
          // skapar startsidan

        public void startSida(){
         label4 = new JLabel();
         panel2 = new JPanel();
         button3 = new JButton("Konton");
         button4 = new JButton("Sparkalkyl");
         button5 = new JButton("Logga Ut");
         button19 = new JButton("Ändra Lösen");
         button20 = new JButton("Ändra Email");
         button3.setBounds(0,100,150,25);
         button4.setBounds(0,140,150,25);
         button5.setBounds(0,5,150,25);
         button19.setBounds(0, 180,150,25);
         button20.setBounds(0,220,150,25);
         button3.addActionListener(this);
         button4.addActionListener(this);
         button5.addActionListener(this);
         button19.addActionListener(this);
         button20.addActionListener(this);

         panel2.setSize(600,600);
         panel2.setLayout(null);
         panel2.add(button3); panel2.add(button4); panel2.add(button5); panel2.add(button19); panel2.add(button20);
         panel2.setBackground(Color.LIGHT_GRAY);
         panel2.setVisible(true);
         frame.add(panel2);
         frame.setTitle("Bank");


        }
        // skapar konto sidan

        public void kontoSida(){
        text1 = new JTextField();
        text2 = new JTextField();
        text3 = new JTextField();
        text4 = new JTextField();
        text5 = new JTextField();
        text6 = new JTextField();
        button6 = new JButton();
        button7 = new JButton();
        button8 = new JButton();
        button9 = new JButton();
        button10 = new JButton();
        button13 = new JButton();
        button14 = new JButton();
        button15 = new JButton();
        button16 = new JButton();
        button17 = new JButton();
        button18 = new JButton();

        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        label11 = new JLabel();

        panel2 = new JPanel();
        panel3 = new JPanel();

        // textfields


        text1.setText((loneKonto.getKontoNummer()));
        text2.setText((loneKonto.getBalans()) + " Kr");

            text4.setText(sparKonto.getKontoNummer());
            text5.setText((sparKonto.getBalans()) + " kr");

        text3.setBounds(325,5,150,25);
        text1.setBounds(150,100,200,25);
        text2.setBounds(150,130,200,25);
        text4.setBounds(150,200,200,25);
        text5.setBounds(150,230,200,25);
        text6.setBounds(325,30,150,25);

        text1.setEditable(false);
        text2.setEditable(false);
        text4.setEditable(false);
        text5.setEditable(false);



        text6.setVisible(false);
        text3.setVisible(false);





        // label
        label1.setText("Dina Konton");
        label1.setBounds(0, 40, 100,25);
        label2.setText("Kontonummer");
        label2.setBounds(0, 100,100,25);
        label3.setText("Balans");
        label3.setBounds(0,130,100,25);
        label4.setText("Insättning");
        label4.setBounds(250,5,90,25);
        label5.setText("Uttag");
        label5.setBounds(250,5,90,25);
        label6.setText("Kontonummer");
        label6.setBounds(0,200,100,25);
        label7.setText("Balans");
        label7.setBounds(0,230,100,25);
        label8.setText("Lönekonto");
        label8.setBounds(0,75,100,25);
        label9.setText("Sparkonto");
        label9.setBounds(0,175,100,25);
        label10.setText("Överföring");
        label10.setBounds(250,5,90,25);
        label11.setText("Mottagares email");
        label11.setBounds(220,30,120,25);



        //döljer lables som endast skall visas om man trycker på vissa knappar
        label4.setVisible(false);
        label5.setVisible(false);
        label10.setVisible(false);
        label11.setVisible(false);

        // button
            button6 = new JButton("Tillbaka");
            button6.setBounds(0,5,100,30);
            button6.addActionListener(this);

            button7.setText("Insättning");
            button7.setBounds(485,100,100,25);
            button7.setFocusable(false);
            button7.addActionListener(this);

            button8.setText("Uttag");
            button8.setBounds(485,130,100,25);
            button8.setFocusable(false);
            button8.addActionListener(this);

            button9.setText("Ok");
            button9.setBounds(485,5,100,25);
            button9.addActionListener(this);
            button9.setVisible(false);

            button10.setText("ok");
            button10.setBounds(485,5,100,25);
            button10.addActionListener(this);
            button10.setVisible(false);

            button13.setText("Insättning");
            button13.setBounds(485, 200,100,25);
            button13.addActionListener(this);
            button13.setFocusable(false);
            button13.setVisible(true);

            button14.setText("Uttag");
            button14.setBounds(485, 230, 100,25);
            button14.setFocusable(false);
            button14.addActionListener(this);
            button14.setVisible(true);

            button15.setText("Ok");
            button15.setBounds(485,5,100,25);
            button15.addActionListener(this);
            button15.setVisible(false);

            button16.setText("Ok");
            button16.setBounds(485,5,100,25);
            button16.addActionListener(this);
            button16.setVisible(false);

            button17.setText("Överföring");
            button17.setBounds(485,70,100,25);
            button17.setVisible(true);
            button17.addActionListener(this);

            button18.setText("ok");
            button18.setBounds(485,5,100,25);
            button18.addActionListener(this);
            button18.setVisible(false);

        // panel
        panel2.setSize(600,600);
        panel2.add(label1); panel2.add(text1); panel2.add(text2); panel2.add(text4); panel2.add(text5);
        panel2.add(label2); panel2.add(label3); panel2.add(button6); panel2.add(button7); panel2.add(button8);
        panel2.add(text3); panel2.add(button9); panel2.add(button10);  panel2.add(label4); panel2.add(label5); panel2.add(label6); panel2.add(label7);
        panel2.add(button13); panel2.add(button14); panel2.add(label8); panel2.add(label9); panel2.add(button15); panel2.add(button16); panel2.add(button17); panel2.add(label10); panel2.add(button18);
        panel2.add(text6); panel2.add(label11);
        panel2.setLayout(null);

        panel2.setBackground(Color.lightGray);
        panel2.setVisible(true);








        frame.add(panel2);




        }
        // skapar sparkonto sidan
        public void sparSida(){
        text1 = new JTextField();
        text2 = new JTextField();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        panel2 = new JPanel();
        button11 = new JButton("Tillbaka");
        button12 = new JButton("Skapa konto");

        slider1 = new JSlider(1,25,1);
        label3.setBounds(300,140,250,30);
        slider1.setBounds(240,180,250,25);
        slider1.setMajorTickSpacing(5);
        slider1.setBackground(Color.BLACK);
        slider1.setForeground(Color.WHITE);
        slider1.setPaintTicks(true);
        slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = slider1.getValue();
                double a = SparKonto.rantaPaRanta(parseDouble(text1.getText()),parseDouble(text2.getText()),value);
                int b = (int) a;
                label3.setText(b + " Värde efter " + slider1.getValue()+" år");
            }
        });

        // Textfield

        text1.setBounds(0,150,200,25);
        text2.setBounds(0,200,200,25);

        // Label
        label1.setBounds(0,120,200,25);
        label2.setBounds(0,175,200,25);
        label1.setText("Startsumma");
        label2.setText("Månadsparande");

        // Button
        button11.setBounds(0,5,100,30);
        button11.setVisible(true);
        button11.addActionListener(this);



        panel2.setSize(600,600);
        panel2.setLayout(null);
        panel2.setBackground(Color.lightGray);

        panel2.add(slider1);
        panel2.add(label1); panel2.add(label2);panel2.add(label3);
        panel2.add(text1); panel2.add(text2); panel2.add(button11);
        panel2.setVisible(true);

        frame.add(panel2);
        }


        public void loggaUt(){ // metod som kallas när användaren klickar på logga ut eller när programmet vill logga ut användaren.
                               // den loggar ut användaren och återgår till login skärmen där använder måste skriva in lösen och mail igen.

           frame.setLocationRelativeTo(null);
           panel1.setVisible(true);


        }


        /*
        Klass som skapar nytt konto den genomför alla nödvändiga kontroller så att konto inte innehåller ogiltig information
        Därefter skapas ett konto med inmatade upggifter och användaren kan då logga in.
         */


    class nyttKonto implements ActionListener{
         int testAlder;
        String [] felMedelande ={"Namn innehåller felaktiga tecken",    "Efternamn innehåller felaktiga tecken",   "Adress innehåller felaktiga tecken" ,
                "Ålder felaktiga, ange ålder i siffror, Högsta ålder 120år",     "Lösenord för kort eller för långt",    "Ålder för hög , högsta ålder 120",
                     "Något gick fel vänligen försök igen"};

         JFrame frame = new JFrame();
         JLabel forNamn = new JLabel("Förnamn");
         JLabel efterNamn = new JLabel("Efternamn");
         JLabel information = new JLabel("Fyll i uppgifterna");
         JLabel Adress = new JLabel("Adress");
         JLabel Alder = new JLabel("Ålder");
         JLabel Epost = new JLabel("Email");
         JLabel klart = new JLabel("Kontot är skapat du kan nu logga in");
         JLabel Losen = new JLabel("Lösen");
         JTextField fornamn;
         JTextField efternamn;
         JTextField adress;
         JTextField alder;
         JTextField epost;
         JTextField losen;
         JButton button3;
         JButton button4;
         JPanel panel1;
         JPanel panel2;


        nyttKonto() {
             fornamn = new JTextField();
             efternamn = new JTextField();
             adress = new JTextField();
             alder = new JTextField();
             epost = new JTextField();
             losen = new JTextField();


             panel1 = new JPanel();
             panel2 = new JPanel();





             // label
             forNamn.setBounds(125, 100, 100, 20);
             efterNamn.setBounds(125, 130, 100, 20);
             Adress.setBounds(125, 160, 100, 20);
             Alder.setBounds(125, 190, 100, 20);
             Epost.setBounds(125,220, 100,20);
             Losen.setBounds(125,250,100,20);
             information.setBounds(250, 70,100,20);
             klart.setBounds(200,160,250,20);




             // Textfields
             fornamn.setBounds(200, 100, 200, 20);
             efternamn.setBounds(200, 130, 200, 20);
             adress.setBounds(200, 160, 200, 20);
             alder.setBounds(200, 190, 200, 20);
             epost.setBounds(200,220,200,20);
             losen.setBounds(200,250,200,20);

             losen.setText("Minst 8 tecken max 12");



             // Button
             button3 = new JButton();
             button3.setBounds(250, 270, 100, 20);
             button3.addActionListener(this);
             button3.setText("Sumbit");
             button3.setFocusable(false);
             button4 = new JButton();
             button4.setBounds(250,200,100,20);
             button4.addActionListener(this);
             button4.setText("Ok");
             button4.setFocusable(false);



             // Frame
             frame.setSize(600, 600);
             frame.setLayout(null);

             // panel
            panel1.setSize(600,600);
            panel1.setLayout(null);


            panel1.add(fornamn); panel1.add(efternamn); panel1.add(adress); panel1.add(alder); panel1.add(epost); panel1.add(losen);
            panel1.add(forNamn); panel1.add(efterNamn); panel1.add(Adress); panel1.add(Alder); panel1.add(Epost); panel1.add(Losen);
            panel1.add(information);
            panel1.add(button3);

            panel1.setVisible(true);
            panel2.setSize(600,600);
            panel2.setLayout(null);
            panel2.add(klart); panel2.add(button4);
            panel2.setVisible(false);

            panel1.setBackground(Color.LIGHT_GRAY);
            panel2.setBackground(Color.LIGHT_GRAY);

            // sätter ihop allt

             frame.add(panel1); frame.add(panel2);
             frame.setTitle("Bank");
             frame.setVisible(true);

         }



         /*
            När användaren  fyllt i info och klickar på submit
            läser actionperfomed av det och programmet hämtar den inmatade datan och skickar till respektive
            set metod och tar emot ett true eller false värde för varje set metod, om den får tillbaka false medelas
            användare om vad den skrivit fel.
            Om användare mata in korrekt data så genomförs set metodern och användaren medelas att konton är klart och att den kan logga in
            när användaren klickar på ok stängs nytt konto klassen ner och användaren kan nu logga in i den tidigare framen.
          */
        @Override
        public void actionPerformed(ActionEvent a) {
            if (a.getSource() == button3) {

                boolean ok = true;
                boolean kontroll = true;
                char [] losenKontrol;





                try {
                    testAlder = Integer.parseInt(alder.getText());

                }catch (NumberFormatException e) {
                     ok = false;
                     felInmatning(3);

                }
                if(ok) {
                    losenKontrol = losen.getText().toCharArray();

                   ok = setLosen(losenKontrol);
                   if(!ok) {
                       felInmatning(4);
                       kontroll = false;
                   }

                   ok = setFornamn(fornamn.getText());
                    if(!ok){
                        felInmatning(0);
                        kontroll = false;
                    }
                   ok = setEfternamn(efternamn.getText());
                    if(!ok){
                        felInmatning(1);
                        kontroll = false;
                    }
                  ok = setAdress(adress.getText());
                    if(!ok){
                        felInmatning(2);
                        kontroll = false;
                    }
                  ok = setAlder(testAlder);
                       if(!ok){
                           felInmatning(5);
                           kontroll = false;

                       }


                    setEmail(epost.getText()); // todo fixa riktig kontroll för epost
                    if(kontroll) {
                        loneKonto = new LoneKonto();
                        sparKonto = new SparKonto();
                        panel1.setVisible(false);
                        panel2.setVisible(true);
                        System.out.println(getFornamn() + " " + getEfternamn() + " " + getAdress() + "\n" + getEmail()
                        + " " + getAlder() + " " + Arrays.toString(getLosen()));
                    }
                }
            }
            if(a.getSource() == button4) {
                try {

                    sparaKonto();
                }catch (IOException b){
                    felInmatning(6);

                }
                frame.dispose();
            }

        }
        private void felInmatning(int a){
            JOptionPane.showMessageDialog(null, felMedelande[a], "Fel",
                    JOptionPane.WARNING_MESSAGE, null);

        }



   } }

