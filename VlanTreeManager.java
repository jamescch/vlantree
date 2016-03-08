package vlantree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VlanTreeManager {
	ArrayList<String> vlan_active;
    Map<String, VlanTree> vlanTreeMap;
    
    public VlanTreeManager(){
        vlanTreeMap = new HashMap<String, VlanTree>();
    }
    
    public void createTree(String vlanId){
        VlanTree vlanTree = new VlanTree(vlanId);
        vlanTreeMap.put(vlanId, vlanTree);
    }
    
    public void addLeafToTree(String vlanId, String portId){
        VlanTree vlanTree = vlanTreeMap.get(vlanId);
        vlanTree.addExtPort(portId);
    }
    
    public void removeLeafFromTree(String vlanId, String portId){
        VlanTree vlanTree = vlanTreeMap.get(vlanId);
        vlanTree.removeExtPort(portId);
        
        String nodeId = portId.substring(0, portId.lastIndexOf(":"));
        vlanTree.removeRecursively(nodeId);
        
        if(vlanTree.hasLeaves() != true){
        	vlanTreeMap.remove(vlanId);
        }
    }
    
    public void addLinkToTree(String vlanId, String srcPort, String dstPort){
        VlanTree vlanTree = vlanTreeMap.get(vlanId);
        vlanTree.addLink(srcPort, dstPort);
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
