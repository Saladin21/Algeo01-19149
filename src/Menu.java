import java.util.Scanner;

public class Menu{
    public static void main(String[] args) {
        /*KAMUS */
        boolean exit = false;
        Scanner baca = new Scanner(System.in);

        /*ALGORITMA */

        while(!exit){
            System.out.println("Menu");
            System.out.println("1. Sistem Persamaan Linier");
            System.out.println("2. Determinan");
            System.out.println("3. Matriks balikan");
            System.out.println("4. Interpolasi Polinom");
            System.out.println("5. Regresi linier berganda");
            System.out.println("6. Keluar");
            int read = baca.nextInt();


            if (read==6){
                exit = true;
            }
        }
        baca.close();

    }
}