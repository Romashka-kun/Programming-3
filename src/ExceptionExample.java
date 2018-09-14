public class ExceptionExample {

    public static void main(String[] args) {

        /*
        Тупой вариант проверки на ошибку
        f = open("a.txt");
        if (last.error() != 0)
             print(last.error(крч ошибка));
             return LUL;
        text = read(f);
        снова блок с ифом
        close(f);
        и снова блок с ифом



        f = open("a.txt");
        txt = read(f);   - exception, если возник, то программа дальше не выполняется.
        close(f);



        public void f(...) {
            исключение              //Два варианта: обработка и явно отказаться от неё

            1) throws Exception
            Тот, кто вызывает f, теперь снова решает каким из двух вариантов работать/
            Если в main(String [] args) throws Exception
            в Java обработка: e.printStackTrace();

            void f() {
                try {
                    exception
                } catch(Exception e) {
                    //если внутри try был exception, то сразу вызывается этот блок
                    e.printStackTrace();
                }

             ошибки, которые могут возникать, касса Throwable. Делятся на Error и Exception. От Error'ов ничего не
             спасет (OutOfMemoryError). Важный Exception: RuntimeException. Например, деление на ноль
             DivisionByZeroException, ArrayOutOfBoundException, NullPointerException. Не RuntimeException: IOException,
             FileNotFoundException.


             try (...) { //заводим переменную, она точно будет close();
             } catch () {
             } finally { //выполняется всегда!!1!
             }

        }

        Оператор throw e;
        throw new Exception("ошибка");
        throw new Exception();

        public class MyException extends Exception {
            throw new MyException();

            catch(MyException e) {
            }
        */
    }
}
