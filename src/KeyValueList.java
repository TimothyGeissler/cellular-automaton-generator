public class KeyValueList {
    private int size;
    private String [] keys;
    private String [] vals;
    public KeyValueList(int size) {
        this.size = size;
        keys = new String[size];
        vals = new String[size];
    }

    public int getSize() {
        return size;
    }

    public void insertKV(int i, String key, String value) {
        keys[i] = key;
        vals[i] = value;
    }

    public String[] getKV(int i) {
        return new String[]{keys[i], vals[i]};
    }

    public String getValue(String key) {
        int index = 0;
        for (String s: keys) {
            if (key.equals(s)) {
                return vals[index];
            }
            index++;
        }
        return null;
    }

}
