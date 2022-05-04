package example;
import java.util.Scanner; 

public class Splitter {

    public static void main(String[] args){
        System.out.println("Enter a sentence specified by spaces only: ");
        // Add your code
        Scanner obj = new Scanner(System.in);
        String line = obj.nextLine();
        String[] splitted = line.split(" ");
        for (int i = 0; i < splitted.length; i++) {
        	System.out.println(splitted[i]);
        }
    }
}
