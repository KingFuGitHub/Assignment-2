public class Group implements Data {
    
    private String name;

    @Override
    public void setName(String name){
        this.name = name;
    }

    @Override
    public String getName(){
        return name;
    }

    public String toString(){
        return name;
    }

}
