package zaraev.epam.com;

public class WorkWithException {

    public void myUncheckedExceptionNotAddNull() {
        throw new MyUncheckedException("Не добавляй нулевой элемент");
    }

    public void myUncheckedExceptionNotRemoveNull() {
        throw new MyUncheckedException("Нельзя удалить то чего нет");
    }

    public void myUncheckedExceptionNotFound() {
        throw new MyUncheckedException("Такого элемента нет");
    }

    public void myCheckedExceptionNotGetNull() {
        try {
            throw new MyCheckedException("Нельзя получить нулевой элемент");
        } catch (MyCheckedException e) {
            e.printStackTrace();
        }
    }

    public void myCheckedExceptionNotFound() {
        try {
            throw new MyCheckedException("Такого элемента нет");
        } catch (MyCheckedException e) {
            e.printStackTrace();
        }
    }
}