package zaraev.epam.com;

public class StringElement {
    int linePositionString;
    String fileName;
    String textString;

    @Override
    public String toString() {
        return "{" +
                "linePositionString=" + linePositionString +
                ", fileName=" + fileName +
                ", fileName=" + textString +
                '}';
    }
}
