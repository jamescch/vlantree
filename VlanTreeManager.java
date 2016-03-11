package vlantree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author James
 * 
 * This class maintains the vlan information in a tree view.
 *
 */
public class VlanTreeManager {
	
//	ArrayList<String> vlan_active;
    Map<Integer, VlanTree> vlanTreeMap;
    
    public VlanTreeManager(){
        vlanTreeMap = new HashMap<Integer, VlanTree>();
//        vlan_active = new ArrayList<String>();
    }
    
    private void createTree(Integer vlanId){
//    	vlan_active.add(vlanId);
    	
        VlanTree vlanTree = new VlanTree(vlanId);
        vlanTreeMap.put(vlanId, vlanTree);
    }
    
    private void removeTree(Integer vlanId){
//    	vlan_active.remove(vlanId);
    	vlanTreeMap.remove(vlanId);
    }
    
    private boolean hasTree(Integer vlanId){
    	return vlanTreeMap.containsKey(vlanId);
    }
    
    public void addLeafToTree(Integer vlanId, String portId){
    	if(!this.hasTree(vlanId)){
    		this.createTree(vlanId);
    	}
    	
    	VlanTree vlanTree = vlanTreeMap.get(vlanId);
        vlanTree.addExtPort(portId);
    }
    
    /**
     * Remove a leaf from a tree and return a list of ports that
     * will be removed during the reconstruction process 
     * 
     * @param vlanId
     * @param portId
     * @return A list of ports to be removed
     */
    public ArrayList<String> removeLeafFromTree(Integer vlanId, String portId){
    	ArrayList<String> portsToRemove = new ArrayList<String>();
    	portsToRemove.add(portId);
    	
    	if(this.hasTree(vlanId)){
    		
	        VlanTree vlanTree = vlanTreeMap.get(vlanId);
	        vlanTree.removeExtPort(portId);
	        
	        String nodeId = portId.substring(0, portId.lastIndexOf(":"));
	        vlanTree.removeRecursively(nodeId, portsToRemove);
	        
	        if(vlanTree.hasLeaves() != true){
	        	this.removeTree(vlanId);
	        }
	        
	        return portsToRemove;
	        
    	}
    	
    	return null;
    }
    
    public void addLinkToTree(Integer vlanId, String srcPort, String dstPort){
    	if(this.hasTree(vlanId)){
	        VlanTree vlanTree = vlanTreeMap.get(vlanId);
	        vlanTree.addLink(srcPort, dstPort);
    	}
    }
    
    public String getRoot(Integer vlanId){
    	return vlanTreeMap.get(vlanId).getRoot();
    }
    
    public void print(Integer vlanId){
        VlanTree vlanTree = vlanTreeMap.get(vlanId);
        if(vlanTree == null){
        	System.out.println("Tree "+vlanId+" is empty");
        	return;
        }
        vlanTree.print();
    }

}
