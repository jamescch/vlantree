package vlanTree;

public class Main {

    public static void main(String[] args) {
        VlanTreeManager vlanTreeManager = new VlanTreeManager();
        vlanTreeManager.createTree("100");
        
        vlanTreeManager.addLeafToTree("100", "openflow:3:1");
        vlanTreeManager.addLeafToTree("100", "openflow:5:1");
        vlanTreeManager.addLinkToTree("100", "openflow:3:2", "openflow:6:3");
        vlanTreeManager.addLinkToTree("100", "openflow:6:5", "openflow:5:2");
        
        vlanTreeManager.addLeafToTree("100", "openflow:3:1");
        vlanTreeManager.addLeafToTree("100", "openflow:4:1");
        vlanTreeManager.addLinkToTree("100", "openflow:3:2", "openflow:6:3");
        vlanTreeManager.addLinkToTree("100", "openflow:6:4", "openflow:4:2");
        
        vlanTreeManager.removeLeafFromTree("100", "openflow:4:1");
        vlanTreeManager.removeLeafFromTree("100", "openflow:5:1");
        
        vlanTreeManager.print("100");
        
        vlanTreeManager.createTree("200");
        
        vlanTreeManager.addLeafToTree("200", "openflow:6:3");
        vlanTreeManager.addLeafToTree("200", "openflow:11:2");
        vlanTreeManager.addLinkToTree("200", "openflow:6:1", "openflow:2:3");
        vlanTreeManager.addLinkToTree("200", "openflow:2:1", "openflow:1:1");
        vlanTreeManager.addLinkToTree("200", "openflow:1:3", "openflow:4:1");
        vlanTreeManager.addLinkToTree("200", "openflow:4:3", "openflow:11:1");
        
        vlanTreeManager.addLeafToTree("200", "openflow:6:3");
        vlanTreeManager.addLeafToTree("200", "openflow:7:2");
        vlanTreeManager.addLinkToTree("200", "openflow:6:1", "openflow:2:3");
        vlanTreeManager.addLinkToTree("200", "openflow:2:4", "openflow:7:1");
        
        vlanTreeManager.removeLeafFromTree("200", "openflow:11:2");
        vlanTreeManager.removeLeafFromTree("200", "openflow:7:2");
        
        vlanTreeManager.print("200");

    }

}
