
import java.util.*;

/*
 * TreeNode contains:
 * i.) contact Name 
 * ii.) contact number[]
 * iii.) reference variables to left & right childs 
*/

// methods which need to be implemented by a class 
interface ContactBookOperations {
    TreeNode createContact(TreeNode root, String contactName, String contactNumber);

    TreeNode searchContact(TreeNode root, String contactName);

    TreeNode deleteContact(TreeNode root, String contactName);

    void displayContact(TreeNode root);

    void updateContact(TreeNode root);
}

public class ContactBook implements ContactBookOperations {

    // before inserting the contact into the ContactBook, check if the contact no. is valid or not
    private boolean phoneNumberValidator(String contactNumber) {
        if (contactNumber.length() != 10)
            return false;
        for (int i = 0; i < contactNumber.length(); i++) {
            int ascii_value = (int) contactNumber.charAt(i);
            if (ascii_value < 48 || ascii_value > 57)
                return false;
        }
        return true;
    }

    private TreeNode FindMax(TreeNode root) {
        if (root == null)
            return null;
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    // creating a new contact (insertion logic used in BST)
    public TreeNode createContact(TreeNode root, String contactName, String contactNumber) {

        // check if the entered phone number is valid or not
        Scanner sc = new Scanner(System.in);
        while (!phoneNumberValidator(contactNumber)) {
            System.out.println("Contact Number entered is not valid!!");
            System.out.print("Enter again: ");
            contactNumber = sc.nextLine();
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
            } else if (comparedValue < 0) {
                if (temp.left == null) {
                    System.out.println("Contact created\n");
                    temp.left = new TreeNode(contactName, contactNumber);
                    break;
                }
                // if temp.left is not null => keep on moving to temp.left until an empty left
                // child is found
                temp = temp.left;
            }
            // if contact with same name already exists
            else {
                System.out.println("Contact with this name already exists!! See the details: ");
                System.out.println("\tName: " + temp.contactName);
                System.out.println("\tContact: " + temp.contacts[0]);

                System.out.print("Do you want to add this number to this contact? type y for yes: ");
                char ch = sc.nextLine().charAt(0);
                if ((ch == 'y' || ch == 'Y') && temp.contacts[1] == null) {
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
        if (root == null)
            return null;
        if (root.contactName.equals(contactName))
            return root;
        else if (contactName.compareTo(root.contactName.toLowerCase()) < 0)
            return searchContact(root.left, contactName);
        return searchContact(root.right, contactName);
    }

    public void displayContact(TreeNode root) {
        if (root == null)
            return;
        displayContact(root.left);
        System.out.println(root.contactName);
        for (String s : root.contacts) {
            if (s != null) {
                System.out.println(s);
            }
        }
        displayContact(root.right);
    }

    public TreeNode deleteContact(TreeNode root, String contactName) {
        if (root == null) 
            return root;
        else if (contactName.compareTo(root.contactName.toLowerCase()) < 0)
            root.left = deleteContact(root.left, contactName);
        else if (contactName.compareTo(root.contactName.toLowerCase()) > 0)
            root.right = deleteContact(root.right, contactName);

        // if contact to be deleted is found
        else {
            // for node with 2 childs
            if (root.left != null && root.right != null) {
                // replace root data with largest element in left subtree(inorder predecessor)
                TreeNode temp = FindMax(root.left);
                root.contactName = temp.contactName;
                root.contacts[0] = temp.contacts[0];
                root.contacts[1] = temp.contacts[1];

                root.left = deleteContact(root.left, root.contactName);
            }
            // if the node has only one child or it is a leaf node
            else {
                if (root.left == null)
                    return root.right;
                if (root.right == null)
                    return root.left;
            }
        }
        return root;
    }

    public void updateContact(TreeNode root) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Do you want to update the Name(y/n): ");
        char c = sc.nextLine().charAt(0);
        if (c == 'y') {
            System.out.print("Enter new Name: ");
            String name = sc.nextLine();
            root.contactName = name;
        }
        System.out.print("Do you want to update the Contact(y/n): ");
        c = sc.nextLine().charAt(0);
        if (c == 'y') {
            System.out.print("Enter new Contact: ");
            String contact = sc.nextLine();
            while (!phoneNumberValidator(contact)) {
                System.out.println("Contact Number entered is not valid!!");
                System.out.print("Enter again: ");
                contact = sc.nextLine();
            }

            if (root.contacts[1] == null) {
                root.contacts[0] = contact;
            } else {
                System.out.print("Which contact do you want to update(1 or 2)?: ");
                int x = Integer.parseInt(sc.nextLine());
                if (x == 1)
                    root.contacts[0] = contact;
                else if (x == 2)
                    root.contacts[1] = contact;
            }
        }
    }

}
