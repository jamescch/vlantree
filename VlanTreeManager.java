package vlantree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VlanTreeManager {
	
	ArrayList<String> vlan_active;
    Map<String, VlanTree> vlanTreeMap;
    
    public VlanTreeManager(){
        vlanTreeMap = new HashMap<String, VlanTree>();
        vlan_active = new ArrayList<String>();
    }
    
    private void createTree(String vlanId){
    	vlan_active.add(vlanId);
    	
        VlanTree vlanTree = new VlanTree(vlanId);
        vlanTreeMap.put(vlanId, vlanTree);
    }
    
    private void removeTree(String vlanId){
    	vlan_active.remove(vlanId);
    	vlanTreeMap.remove(vlanId);
    }
    
    private boolean hasTree(String vlanId){
    	return vlanTreeMap.containsKey(vlanId);
    }
    
    public void addLeafToTree(String vlanId, String portId){
    	if(!this.hasTree(vlanId)){
    		this.createTree(vlanId);
    	}
    	
    	VlanTree vlanTree = vlanTreeMap.get(vlanId);
        vlanTree.addExtPort(portId);
    }
    
    public void removeLeafFromTree(String vlanId, String portId){
    	if(this.hasTree(vlanId)){
    		
	        VlanTree vlanTree = vlanTreeMap.get(vlanId);
	        vlanTree.removeExtPort(portId);
	        
	        String nodeId = portId.substring(0, portId.lastIndexOf(":"));
	        vlanTree.removeRecursively(nodeId);
	        
	        if(vlanTree.hasLeaves() != true){
	        	this.removeTree(vlanId);
	        }
	        
    	}
    }
    
    public void addLinkToTree(String vlanId, String srcPort, String dstPort){
    	if(this.hasTree(vlanId)){
	        VlanTree vlanTree = vlanTreeMap.get(vlanId);
	        vlanTree.addLink(srcPort, dstPort);
    	}
    }
    
    public Node getRoot(String vlanId){
    	return vlanTreeMap.get(vlanId).getRoot();
    }
    
    public void print(String vlanId){
        VlanTree vlanTree = vlanTreeMap.get(vlanId);
        if(vlanTree == null){
        	System.out.println("Tree "+vlanId+" is empty");
        	return;
        }
        vlanTree.print();
    }

}
