public class IOpackage {
    /*В Java две библиотеки для IO
    пакет java.io - старый
    пакет java.nio - новый
    Старый:
        InputStream (входной поток байтов) - reader (символов)
        OutputStream (выходной поток байтов) - writer (символов)
    Кодировка - таблица соответствия символов и числового представления кода.
    Примеры:
    ASCII, cp 866, windows-1251, KOI 8-r, UTF-8.

    ASCII (7 bit)
        0 -
        10 - перевод строки
        19 - возврат коретки
        32 - пробел
        48 - "0"
        57 - "9"
        65 - "A"
        116 - "z"

    cp 866 - ASCII и вторая половина 128-255 (русские буквы, псевдографика).

    windows-1251 - -||- русские буквы и буквы кириллической письменности

    koi8-r

    UTF-8 - ASCII совместное представление UNICODE
           0..127 - ASCII символ


    InputStream <-> Reader
    OutputStream <-> Writer
    <-> - кодировка

    В Java кодировка по умолчанию: UTF-8 или windows-1251. Всегда надо указывать явно.

    (байты) <-> (символы)

    Что умеет InputStream?
    - int read() - читает 1 байт  | -1, если конец
    0...255
    - int read(byte[] buf) - пишет несколько байт в буффер
    - close() - не вызывайте сами. Через try(){}

    Что умеет OutputStream?
    write(byte b)
    write(byte[] buf)
    close()
    flush() <-- протолкнуть. Заставить записать данные на устройство.

    Reader, Writer - аналогично.

    FileInputStream - IS, связанный с файлом
    FileOutputStream - OS -||-
    FileReader - R -||-    не рекомендуется
    FileWriter - W -||-    не рекомендуется
    OutputStreamWriter \
    InputStreamReader -> превращают OS  в Writer, IS в Reader

    Прочитать текст из файла
    try (Reader in = new InputStreamReader(new FileInputStream("a.txt"), "UTF-8"))

    Buffered: InputStream, OutputStream, Reader, Writer - обёрка для производительности + доп. возможности
    в BufferedWriter есть метод newLine() - перевод строки.

    try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("a.txt"), "UTF-8")))
       in.newLine();



    *****NIO*****

    в java.io
    класс File <-> путь на диске

    в java.nio появился аналогичный способ
    класс Path - путь

    можно превращать друг в друга
    new File("a.txt").toPath();

    сразу Path:
       Paths.get("...");
       Paths.get("c:/windows");
       Paths.get("c:/windows", "a.txt");

    Paths - вспомогательный класс, чтобы получить Path

    Класс Files - вспомогательный класс для работы с файлами (см. помощь)
    Files.move(source, destination, ...) - удобнее, чем File.move()
                               StandardCopyOption.REPLACE_EXISTING

    byte[] Files.readAllBytes(Path);
    String[] Files.readAllBytes(Path);

    Files.write(Path, byte[], ...);
    Files.write(Path, __, charset, option);

    Хотим прочитать файл, как одну строку.

    byte[] allBytes = Files.readAllBytes(...);
    */
}
