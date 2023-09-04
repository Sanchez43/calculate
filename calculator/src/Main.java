import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите арифметическое выражение");
        System.out.println(Calculator.calc(sc.nextLine()));
    }
}