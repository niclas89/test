

public class SparKonto extends LoneKonto {



    public static double rantaPaRanta(double startvarde, double manadsparande, int ar ){



        double ranta  = 0;
        for(int i = 0; i<ar;  i++){
            ranta += (startvarde+ ranta +manadsparande*12*i) * Math.pow(1.03,1)  -(ranta + startvarde + manadsparande*12*i);

        }
       double resultat =  startvarde + manadsparande*12*ar+ ranta + 500*ar;







        return  resultat;
    }
    SparKonto(){
        setBalans(100);


    }


}
