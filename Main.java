import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
    checkStrength();
    }
    private static void checkStrength() throws IOException {
        System.out.println("Welcome to the password strenght checker");
        System.out.println("Please input your password");
        String password = getPassword();
        File file = new File("src/10-million-password-list-top-1000000.txt");
        Scanner scanner = new Scanner(file);
        int desiredLength = 8;
        if (file.exists()){
            boolean password_found = false;
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                if (line.equals(password)){
                    password_found = true;
                }
            }
            if (password_found){
                System.out.println("Your password is to easy to crack!");
                System.out.println("Please choose another one.");
                rerunStrengthChecker();
            }else if (password.length()>=desiredLength){
                checkCharacters(password);
            }else {
                System.out.println("Your password is to short!");
                System.out.println("Please choose another one.");
                rerunStrengthChecker();
            }
        }else System.out.println("File not found");

    }
    private static void checkCharacters(String password) throws IOException {
        int upper_Case=0;
        int lower_Case=0;
        int special=0;
        int digits=0;

        for (char c : password.toCharArray()){
            if (Character.isUpperCase(c)){
                upper_Case++;
            }
            else if (Character.isLowerCase(c)){
                lower_Case++;
            }
            else if (Character.isDigit(c)){
                digits++;
            }else special++;
        }
        if (upper_Case==0){
            System.out.println("Your password should have at least one upper case character");
            rerunStrengthChecker();
        }
        else if (lower_Case==0){
            System.out.println("Your password should have at least one lower case character");
            rerunStrengthChecker();
        }
        else if (special==0){
            System.out.println("Your password should have at least one special character");
            rerunStrengthChecker();}
        else if (digits==0){
            System.out.println("Your password should have at least one digit character");
            rerunStrengthChecker();}
        else System.out.println("Your password is strong!");

    }
    private static void rerunStrengthChecker() throws IOException {
        String password = getPassword();
        File file = new File("src/10-million-password-list-top-1000000.txt");
        Scanner scanner = new Scanner(file);
        if (file.exists()){
            boolean password_found = false;
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                if (line.equals(password)){
                    password_found = true;
                }
            }
            if (password_found){
                System.out.println("Your password is to easy to crack!");
                System.out.println("Please choose another one.");
                rerunStrengthChecker();
            }else checkCharacters(password);
        }else System.out.println("File not found");
    }

    private static String getPassword(){
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}