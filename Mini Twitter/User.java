
public class User implements Data {

    private String name;

    @Override
    public String getName() {
        return name.toString();
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

}
