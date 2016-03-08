package vlantree;

import java.util.HashMap;
import java.util.Map;

public class Node {
    
    private String id;
    private int extCount;
    private int intCount;
    private Map<String, Link> linkMap;
    private Map<String, Port> extPortMap;
    
    public Node(String id){
        this.id = id;
        linkMap = new HashMap<String, Link>();
        extPortMap = new HashMap<String, Port>();
    }
    
    public String getId(){
        return id;
    }
    
    public boolean hasOnlyOneLink(){
        if(extCount == 0 && intCount == 1){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean isOrphan(){
    	if(extCount == 0 && intCount == 0){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    public Map<String, Link> getLinks(){
        return linkMap;
    }
    
    public boolean containsLink(String portId){
        return linkMap.containsKey(portId);
    }
    
    public void addExtPort(Port port){
        extPortMap.put(port.getId(), port);
        extCount = extPortMap.size();
    }
    
    public void removeExtPort(String portId){
        extPortMap.remove(portId);
        extCount = extPortMap.size();
    }
    
    public void addLink(Link link){
        linkMap.put(link.getId(), link);
        intCount = linkMap.size();              
    }
    
    public void removeLink(String linkId){
        linkMap.remove(linkId);
        intCount = linkMap.size();  
    }
    
    public void print(){
        System.out.println("\t node-id: " + id + " ext: " + extCount + " int: " + intCount);
        
        for(String key : linkMap.keySet()){
            Link link = linkMap.get(key);
            link.print();
        }
        
        for(String key : extPortMap.keySet()){
            Port port = extPortMap.get(key);
            port.print();
        }
    }
    
}
