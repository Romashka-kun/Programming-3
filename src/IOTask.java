import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IOTask {

    private static void write(String text, String encoding) {
        try (Writer out = new OutputStreamWriter(new FileOutputStream("tests/text_" + encoding + ".txt"), encoding)) {
            out.write(text);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e);
        }
    }

    private static void readFile(String encoding) {
        try (PrintStream out = new PrintStream("tests/text_" + encoding + ".bin")) {
            byte[] b = Files.readAllBytes(Paths.get("tests", "text_" + encoding + ".txt"));
            for (byte a : b) {
                if (a < 0)
                    out.print(String.valueOf(a + 256) + " ");
                else
                    out.print(String.valueOf(a) + " ");

            }
        } catch (IOException e) {
            System.out.println("lalalal: " + e);
        }
    }

    private static void koi7r(String encoding) {
        try (FileOutputStream out = new FileOutputStream("tests/text_koi7r.txt")) {
            byte[] b = Files.readAllBytes(Paths.get("tests", "text_" + encoding + ".txt"));
            for (byte a : b) {
                if (a < 0)
                    a += 128;
                out.write(a);
            }
        } catch (IOException e) {
            System.out.println("lalalal: " + e);
        }
    }

    public static void main(String[] args) {
        String text = "Предполагается, что эти файлы лежат в одном заранее выбранном вами каталоге.";
        String encoding1 = "utf-8";
        String encoding2 = "windows-1251";
        String encoding3 = "koi8-r";
        write(text, encoding1);
        write(text, encoding2);
        write(text, encoding3);
        readFile(encoding1);
        readFile(encoding2);
        readFile(encoding3);
        koi7r(encoding3);

    }
}
