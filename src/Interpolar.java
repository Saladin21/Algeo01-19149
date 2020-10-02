import java.io.*;
import java.util.Scanner;

public class Interpolar{
    public static void inputUser(Matriks x, Matriks y, Float[] X) {
        // KAMUS LOKAL
        System.out.print("Masukkan derajat polinom: ");
        int n = Menu.baca.nextInt();
        Matriks.MakeEmpty(x, 1, n + 1);
        Matriks.MakeEmpty(y, 1, n + 1);

        // ALGORITMA
        for (int i = 0; i <= n; i++) {
            System.out.print("Masukkan nilai titik ke-"+i+": ");
            x.isi[0][i] = Menu.baca.nextFloat();
            y.isi[0][i] = Menu.baca.nextFloat();
        }
        System.out.print("Masukkan nilai X dari titik yang akan di taksir: ");
        X[0] = Menu.baca.nextFloat();
    }

    public static void inputFile(Matriks x, Matriks y, Float[] X, String file) throws FileNotFoundException {
        // KAMUS LOKAL
        FileInputStream data = new FileInputStream(file);
        Scanner baca = new Scanner(data);
        int n = baca.nextInt();
        Matriks.MakeEmpty(x, 1, n + 1);
        Matriks.MakeEmpty(y, 1, n + 1);

        // ALGORITMA
        for (int i = 0; i <= n; i++) {
            x.isi[0][i] = baca.nextFloat();
            y.isi[0][i] = baca.nextFloat();
        }
        X[0] = baca.nextFloat();
        // tinggal pake fungsi dari SPL
        baca.close();

    }

    public static void interpolar(Matriks x, Matriks y, Matriks M, Float[] X, Float[] Y) {
        // KAMUS LOKAL
        Matriks Mtemp = new Matriks();
        int i, j;
        // ALGORITMA
        Matriks.MakeEmpty(Mtemp, x.KolEff, x.KolEff + 1);
        for (i = 0; i < Mtemp.BrsEff; i++) {
            Mtemp.isi[i][0] = 1;
            for (j = 1; j < Mtemp.KolEff - 1; j++) {
                Mtemp.isi[i][j] = Mtemp.isi[i][j - 1] * x.isi[0][i];
            }
            Mtemp.isi[i][Mtemp.LastIdxKol] = y.isi[0][i];
        }
        SPL.Gauss(Mtemp, M);
    }

    public static void output(Matriks M, Float[] X, Float[] Y) {
        // KAMUS LOKAL
        float Xtemp = 1f;
        int j;
        //ALGORITMA
        System.out.print("\npersamaan polinom yang terbentuk:\ny = ");
        Y[0] = 0f;
        for (j = 0; j < M.BrsEff; j++) {
            if(j==0){
                System.out.printf("%.2f", M.isi[j][0]);
            }
            else{
                System.out.printf(" + %.2fx^%d", M.isi[j][0], j);
            }
            Y[0] += M.isi[j][0] * Xtemp;
            Xtemp *= X[0];
        }
        System.out.print("\n");
        System.out.print("nilai dari y untuk x = "+ X[0] +" adalah " + Y[0] + "\n\n");
    }

    public static void outputFile(Matriks M, Float[] X, Float[] Y, String File) {
        // KAMUS LOKAL
        float Xtemp = 1;
        int j;
        PrintWriter file;
        //ALGORITMA
        try{
            file = new PrintWriter(File);
            Y[0] = 0f;
            System.out.print("\npersamaan polinom yang terbentuk:\ny = ");
            file.print("\npersamaan polinom yang terbentuk:\ny = ");
            for (j = 0; j < M.BrsEff; j++) {
                if(j==0){
                    System.out.printf("%.2f", M.isi[j][0], j);
                    file.printf("%.2f", M.isi[j][0], j);
                }
                else{
                    System.out.printf(" + %.2fx^%d", M.isi[j][0], j);
                    file.printf(" + %.2fx^%d", M.isi[j][0], j);
                }
                Y[0] += M.isi[j][0] * Xtemp;
                Xtemp *= X[0];
            }
            System.out.print("\n");
            file.print("\n");
            System.out.print("nilai dari y untuk x = "+ X[0] +" adalah " + Y[0] + "\n\n");
            file.print("nilai dari y untuk x = "+ X[0] +" adalah " + Y[0] + "\n\n");
            file.close();
        }
        catch(IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
    }

    public static float sk7(Float f){
        return (float)((Math.pow(f, 2) + Math.pow(f, 0.5))/(Math.pow(Math.E, f) + f));
    }

    public static void main(String[] args) throws FileNotFoundException {
        // KAMUS
        Matriks x = new Matriks(), y = new Matriks(), M = new Matriks();
        Float[] X = new Float[]{0f}, Y = new Float[]{0f};
        PrintWriter file;
        float i;
        int n;
        //ALGORITMA
        file = new PrintWriter("datask7.txt");
        n =Menu.baca.nextInt();
        file.print(n +"\n");
        for(i = 0; i<=n; i++){
            file.printf("%f %f\n", 0+(i*2/n), sk7(0+(i*2/n)));
        }
        file.print(0f);
        file.close();
        inputFile(x, y, X, "datask7.txt");
        interpolar(x, y, M, X, Y);
        output(M, X, Y);
        System.out.printf("\n%f\n", sk7(0f));

    }

}