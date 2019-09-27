public class Currency {
    private int id;
    private String code;
    private String name_full;
    private String name_short;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameFull() {
        return name_full;
    }

    public void setNameFull(String name_full) {
        this.name_full = name_full;
    }

    public String getNameShort() {
        return name_short;
    }

    public void setNameShort(String name_short) {
        this.name_short = name_short;
    }
}
