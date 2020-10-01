import java.util.Scanner;
import java.io.*;

public class Menu{
    static final Scanner baca = new Scanner(System.in);

    public static String bacaNamaFile(){
        String file;
        System.out.println("Masukkan nama file: ");
        file = baca.nextLine();
        return file;
    }

    public static boolean darifile(){
        int darifile = 0;
        while(darifile<1 || darifile>2){
            System.out.println("Mau baca input dari mana?");
            System.out.println("1. User");
            System.out.println("2. File");
            darifile = baca.nextInt();
            baca.nextLine();
        }
        return darifile==1;
    }

    public static void menujuFile(Matriks M){
        //KAMUS LOKAL
        int menujufile = 0;
        String NamaFile;
        File myObj;
        while(menujufile<1 || menujufile>2){
            System.out.println("Apakah anda ingin memasukkan hasil ke dalam file?");
            System.out.println("1. Ya");
            System.out.println("2. Tidak");
            menujufile = baca.nextInt();
            baca.nextLine();
            if(menujufile==1){
                try {
                    System.out.print("Masukkan Nama file: ");
                    NamaFile = Menu.baca.nextLine();
                    myObj = new File(NamaFile);
                    if (myObj.createNewFile()) {
                      Matriks.TulisFile(M, NamaFile);
                    } 
                    else {
                      System.out.println("Nama file telah digunakan.");
                    }
                } 
                catch (IOException e) {
                    System.out.println("Ini adalah pesan error");
                    //e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) throws FileNotFoundException{
        /*KAMUS */
        boolean exit = false;
        Scanner baca = new Scanner(System.in);
        int spl = 0, read = 0;
        String file;
        /*ALGORITMA */

        while(!exit){
            System.out.println("Menu");
            System.out.println("1. Sistem Persamaan Linier");
            System.out.println("2. Determinan");
            System.out.println("3. Matriks balikan");
            System.out.println("4. Interpolasi Polinom");
            System.out.println("5. Regresi linier berganda");
            System.out.println("6. Keluar");
            read = baca.nextInt();
            spl = 0;

            if(read == 1){
                while(spl<1 || spl>4){
                    System.out.println("1. Metode Eliminasi Gauss");
                    System.out.println("2. Metode Eliminasi Gauss-Jordan");
                    System.out.println("3. Metode Matriks balikan");
                    System.out.println("4. Kaidah Cramer");
                    spl = baca.nextInt();
                }
                if(spl==1){
                    file = bacaNamaFile();
                    System.out.print(file);
                }
                else if(spl==2){
                    
                }
                else if(spl==3){
                    
                }
                else if(spl==3){
                    
                }
            }
            else if (read==2){
                while(spl<1 || spl>2){
                    System.out.println("1. Reduksi Baris");
                    System.out.println("2. Ekspansi Kofaktor");
                    spl = baca.nextInt();
                }
                if(spl==1){

                }
                else if(spl==2){
                    
                }
            }
            else if (read==3){

            }
            else if (read==4){
                Matriks x = new Matriks(), y = new Matriks(), M = new Matriks();
                Float[] X = new Float[]{0f}, Y = new Float[]{0f};
                if(darifile()){
                    Interpolar.inputUser(x, y, X);
                }
                else{
                    Interpolar.inputFile(x, y, X, bacaNamaFile()); 
                }
                Interpolar.interpolar(x, y, M, X, Y);
                Interpolar.output(M, X, Y);
                menujuFile(M);
            }
            else if (read==5){
                Matriks x = new Matriks(), y = new Matriks(), M = new Matriks(), X = new Matriks();
                Float[] Y = new Float[]{0f};
                if(darifile()){
                    Regresi.inputUser(x, y, X);
                }
                else{
                    Regresi.inputFile(x, y, X, bacaNamaFile()); 
                }
                Regresi.regresi(x, y, M, X);
                Regresi.output(M, X, Y);
            }
            else if (read==6){
                exit = true;
            }
        }
        baca.close();

    }
}