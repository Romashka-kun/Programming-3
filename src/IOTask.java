import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;

public class IOTask {

    private static void write(String text, String encoding) {
        try (Writer out = new OutputStreamWriter(new FileOutputStream("./out/text_" + encoding + ".txt"), encoding)) {
            out.write(text);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e);
        }
    }

    private static void readFile(String encoding) {
        try (FileOutputStream out = new FileOutputStream("./out/text_" + encoding + ".bin")) {
            byte[] b = Files.readAllBytes(new File("./out/text_" + encoding + ".txt").toPath());
            String str = Arrays.toString(b).replace(",", "").replace("[", "")
                    .replace("]", "");
            out.write(str.getBytes());
        } catch (IOException e) {
            System.out.println("lalalal: " + e);
        }
    }

    private static void koi7r(String encoding) {
        try (FileOutputStream out = new FileOutputStream("./out/text_koi7r.txt")) {
            byte[] b = Files.readAllBytes(new File("./out/text_" + encoding + ".txt").toPath());
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
