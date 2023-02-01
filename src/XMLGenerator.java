import java.util.Arrays;

public class XMLGenerator {
    public XMLGenerator() {
    }

    public void printXML(String path, KeyValueList kvl) {
        System.out.println("saving data to: " + path);
        for (int i = 0; i < kvl.getSize(); i++) {
            System.out.println(Arrays.toString(kvl.getKV(i)));
        }
    }
}
