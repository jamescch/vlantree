package vlanTree;

public class Link {

    private String id;
    private String src;
    private String dst;
    
    public Link(String src, String dst){
        id = src;
        this.src = src;
        this.dst = dst;
    }
    
    public String getId(){
        return id;
    }
    
    public String getSrc(){
        return src;
    }
    
    public String getDst(){
        return dst;
    }
    
    public void print(){
        System.out.println("\t\t link-id: " + id);
    }
    
}
