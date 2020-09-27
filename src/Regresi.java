import java.util.Scanner;

public class Regresi{
    public static void main(String[] args){
        //KAMUS
        Scanner baca = new Scanner(System.in);
        int y, n;
        int[] x;
        //ALGORITMA
        n = baca.nextInt();
        x = new int[n];
        for(int i = 0; i<=n; i++){
            x[i] = baca.nextInt();
        }
        y = baca.nextInt();
        // tinggal pake fungsi dari SPL
        baca.close();

    }
}