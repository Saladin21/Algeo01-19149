import java.util.Scanner;

public class Interpolar{
    public static void main(String[] args){
        //KAMUS
        Scanner baca = new Scanner(System.in);
        int n = baca.nextInt();
        int[] x = new int[n+1];
        int[] y = new int[n+1];
        //ALGORITMA
        for(int i = 0; i<=n; i++){
            x[i] = baca.nextInt();
            y[i] = baca.nextInt();
        }
        // tinggal pake fungsi dari SPL
        baca.close();

    }
}