import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
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

    private static void renameFile(List<String> list) {
        String fName = "";
        for (int i = 0; i < list.size(); i++) {
            try {
                fName = list.get(i);
                Path f = new File("./out/" + fName).toPath();
                Path f1 = new File("./out/" + i + fName).toPath();
                Files.move(f, f1, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Файл " + fName + " успешно переименован");
            } catch (IOException e) {
                System.out.println("Не удалось переименовать файл " + fName);
            }
//            } catch (DirectoryNotEmptyException e) {
//                System.out.println();
//            }
        }
    }

    public static void main(String[] args) {

//        for (int i = 1; i <= 10; i++) {
//            try {
//                f(i);
//                System.out.println(i + " - нет исключения");
//            } catch (Exception e) {
//                System.out.println(i + " - есть исключение");
//            }
//        }
//
//        int x = readInt("Введите x");
//        int y = readInt("Введите y");
//        System.out.println("x + y = " + (x + y));


        List<String> list = Arrays.asList("a.txt", "b.txt", "c.txt", "d.txt");
        renameFile(list);


    }
}
