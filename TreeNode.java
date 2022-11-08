public class TreeNode {
  public String contactName;
    public String contacts[];
    public TreeNode left;
    public TreeNode right;
    public TreeNode(String contactName, String contactNumber) {
        this.contactName = contactName;
        contacts = new String[2];
        contacts[0] = contactNumber;
        contacts[1] = null;
        left = null;
        right = null;
    }
}
