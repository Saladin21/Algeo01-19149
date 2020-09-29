import java.util.Scanner;

public class Interpolar{
    public static void input(Matriks x, Matriks y, Float X){
        //KAMUS LOKAL
        Scanner baca = new Scanner(System.in);
        int n = baca.nextInt();
        Matriks.MakeEmpty(x, 1, n+1);
        Matriks.MakeEmpty(y, 1, n+1);

        //ALGORITMA
        for(int i = 0; i<=n; i++){
            x.isi[0][i] = baca.nextFloat();
            y.isi[0][i] = baca.nextFloat();
        }
        X = baca.nextFloat();
        // tinggal pake fungsi dari SPL
        baca.close();

    }
    public static void interpolar(Matriks x, Matriks y, Float X, Float Y){
        //KAMUS LOKAL
        Matriks M = new Matriks();
        Matriks Mtemp = new Matriks();
        float Xtemp = 1;
        //ALGORITMA
        Matriks.MakeEmpty(Mtemp, x.KolEff, x.KolEff+1);
        for(int i = 0; i<M.BrsEff; i++){
            Mtemp.isi[i][0] = 1;
            for(int j = 1; j<M.KolEff-1; j++){
                Mtemp.isi[i][j] = Mtemp.isi[i][j-1]*x.isi[0][i];
            }
            Mtemp.isi[i][M.KolEff-1] = y.isi[0][i];
        }

        SPL.Gauss(Mtemp, M);

        Y = 0;
        for(int i = 0; i<=n; i++){
            Y += M.isi[0][i]*Xtemp;
            Xtemp *= X;
        }

    }
    public static void output(Float Y){
        System.out.print(Y);
    }
}