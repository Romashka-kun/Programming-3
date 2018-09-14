import java.util.Scanner;

public class ExceptionTask {

    private static int readInt(String message) {
        Scanner s = new Scanner(System.in);
        while (true) {
            try  {
                System.out.println(message);
                return Integer.parseInt(s.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Попробуй ещё раз");
            }
        }
    }

    private static void f(int x) throws Exception {

        if (x % 2 == 0)
            throw new Exception("EvenException");
    }



    public static void main(String[] args) {

        for (int i = 1; i <= 10; i++) {
            try {
                f(i);
                System.out.println(i + " - нет исключения");
            } catch (Exception e) {
                System.out.println(i + " - есть исключение");
            }
        }

        int x = readInt("Введите x");
        int y = readInt("Введите y");
        System.out.println("x + y = " + (x + y));

    }
}
