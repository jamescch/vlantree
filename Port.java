package vlantree;

public class Port {

    private String id;
    
    public Port(String id){
        this.id = id;
    }
    
    public String getId(){
        return id;
    }

    public void print() {
        System.out.println("\t\t external port-id: " + id);
        
    }
    
}
