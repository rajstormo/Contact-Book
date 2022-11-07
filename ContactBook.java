
import java.util.*;

/*
 * Node contains:
 * i.) contact Name 
 * ii.) contact number
 * iii.) reference variables to left & right childs 
*/


class TreeNode {
    public String contactName;
    public String contacts[];
    public TreeNode left;
    public TreeNode right;
    public TreeNode(String contactName, String contactNumber) {
        this.contactName = contactName;
        contacts = new String[2];
        contacts[0] = contactNumber;
        left = null;
        right = null;
    }
}

// methods which need to be implemented by a class 
interface ContactBookOperations {
    TreeNode createContact(TreeNode root, String contactName, String contactNumber);
    TreeNode searchContact(TreeNode root, String contactName);
    // TreeNode deleteContact(TreeNode root, String contactName);
    void displayContact(TreeNode root);
    // void updateContact(TreeNode root);
}

public class ContactBook implements ContactBookOperations{

    //before inserting the contact into the ContactBook, check if the contact no. is valid or not
    private boolean phoneNumberValidator(String contactNumber) {
        if (contactNumber.length() != 10) return false;
        for (int i = 0; i < contactNumber.length(); i++) {
            int ascii_value = (int)contactNumber.charAt(i);
            if (ascii_value < 48 || ascii_value > 57)
                return false;
        }
        return true;
    }


    // creating a new contact (insertion logic in BST)
    public TreeNode createContact(TreeNode root, String contactName, String contactNumber) {

        //check if the entered phone number is valid or not
        Scanner sc = new Scanner(System.in);
        while (!phoneNumberValidator(contactNumber)) {
            System.out.println("Contact Number entered is not valid!!");
            System.out.print("Enter again: ");
            contactNumber = sc.next();
        }
        // if contactBook has 0 contacts
        if (root == null) {
            System.out.println("Contact created\n");
            return new TreeNode(contactName, contactNumber);
        }
        
        // if it has more than 0 contacts
        TreeNode temp = root;
        while (temp != null) {
            int comparedValue = contactName.toLowerCase().compareTo(temp.contactName.toLowerCase());
            if (comparedValue > 0) {
                if (temp.right == null) {
                    System.out.println("Contact created\n");
                    temp.right = new TreeNode(contactName, contactNumber);
                    break;
                }
                temp = temp.right;
            }
            else if (comparedValue < 0) {
                if (temp.left == null) {
                    System.out.println("Contact created\n");
                    temp.left = new TreeNode(contactName, contactNumber);
                    break;
                }
                // if temp.left is not null => keep on moving to temp.left until an empty left child is found
                temp = temp.left;
            }
            // if contact with same name already exists 
            else {
                System.out.println("Contact with this name already exists!! See the details: ");
                System.out.println("\tName: " + temp.contactName);
                System.out.println("\tContact: " + temp.contacts[0]);
                System.out.print("Do you want to add this number to this contact? type y for yes: ");
                char ch = sc.next().charAt(0);
                if (ch == 'y' || ch == 'Y') {
                    temp.contacts[1] = contactNumber;
                    System.out.println("New contact Number added\n");
                } else {
                    System.out.println("Contact Number not added\n");
                }
                temp = temp.left;
            }
        }
        return root;
    }

    public TreeNode searchContact(TreeNode root, String contactName) {
        if (root == null) return null;
        if (root.contactName.equalsIgnoreCase(contactName)) return root;
        else if (contactName.compareTo(root.contactName) < 0)
            return searchContact(root.left, contactName);
        return searchContact(root.right, contactName);
    }

    // /* 
    public void displayContact(TreeNode root) {
        if (root == null) return;
        displayContact(root.left);
        System.out.println(root.contactName);
        for (String s : root.contacts) {
            if (s != null) {
                System.out.println(s);
            }
        }
        displayContact(root.right);
    }
    // */

    public void displayContact(TreeNode root, int x) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode curr = q.remove();
            System.out.print(curr.contactName + " ");
            if (curr.left != null) {
                q.add(curr.left);
            }
            if (curr.right != null) {
                q.add(curr.right);
            }
        }
    }
}