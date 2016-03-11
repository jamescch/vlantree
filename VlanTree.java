package vlantree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VlanTree {
    
    private Integer id; // the vlan
    private Map<String, Node> nodeMap;
    
    
    public VlanTree(Integer id){
        this.id = id;
        nodeMap = new HashMap<String, Node>();
    }
    
    public void addLink(String srcPort, String dstPort){
        
        String srcNodeId;
        String dstNodeId;
        
        srcNodeId = srcPort.substring(0, srcPort.lastIndexOf(":"));
        dstNodeId = dstPort.substring(0, dstPort.lastIndexOf(":"));
        
        // If node is not in tree, add it.
        if(!nodeMap.containsKey(srcNodeId)){
            Node node = new Node(srcNodeId);
            nodeMap.put(node.getId(), node);
        }
        
        if(!nodeMap.containsKey(dstNodeId)){
            Node node = new Node(dstNodeId);
            nodeMap.put(node.getId(), node);
        }
        
        Node srcNode = nodeMap.get(srcNodeId);
        Node dstNode = nodeMap.get(dstNodeId);
        
        if(!srcNode.containsLink(srcPort)){ // if src does not contains the link, that implies dst also does not.            
            srcNode.addLink(new Link(srcPort, dstPort));          
            dstNode.addLink(new Link(dstPort, srcPort));
        }
     
    }
    
    public void addExtPort(String portId){
        
        String nodeId;
        
        nodeId = portId.substring(0, portId.lastIndexOf(":"));
        
        if(!nodeMap.containsKey(nodeId)){            
            Node node = new Node(nodeId);
            nodeMap.put(node.getId(), node);
        }
        
        Node node = nodeMap.get(nodeId);
        
        node.addExtPort(new Port(portId));
        
    }
    
    public void removeExtPort(String portId){
        String nodeId = portId.substring(0, portId.lastIndexOf(":"));
        
        Node node = nodeMap.get(nodeId);
        node.removeExtPort(portId);
    }
    
    public void removeRecursively(String nodeId, ArrayList<String> portsToRemove){
        Node node = nodeMap.get(nodeId);
        
        if(node.hasOnlyOneLink()){
            Map<String, Link> links = node.getLinks(); 
            
            String linkId = links.keySet().iterator().next();
            Link link = links.get(linkId);
            
            // remove ports of the link
            portsToRemove.add(link.getSrc());
            portsToRemove.add(link.getDst());
            
            String nextPortId = link.getDst();
            String nextNodeId = nextPortId.substring(0, nextPortId.lastIndexOf(":"));
            
            Node nextNode = nodeMap.get(nextNodeId);
            nextNode.removeLink(nextPortId);
            
            nodeMap.remove(nodeId);
            removeRecursively(nextNodeId, portsToRemove);
            
        }else{
        	return;
        }
    }
    
    public boolean hasLeaves(){
    	if(nodeMap.size() == 1){
    		String nodeId = nodeMap.keySet().iterator().next();
    		Node node = nodeMap.get(nodeId);
    		
    		if(node.isOrphan()){
    			return false;
    		}
    	}
    	return true;
    }
        
    public String getRoot(){
    	return nodeMap.get(nodeMap.keySet().iterator().next()).getId();
    }
    
    public void print(){
        System.out.println("tree-id: " + id);
        
        for(String key : nodeMap.keySet()){
            Node node = nodeMap.get(key);
            node.print();
        }
    }
    
    
}
