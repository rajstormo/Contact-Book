
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        TreeNode root = null;
        ContactBook cb = new ContactBook();
        
        int choice;
        String name, contactNo;
        System.out.println("CONTACT BOOK");
        
        // /* 
        while (true) {
            System.out.println("\n1. Create a New Contact");
            System.out.println("2. Search for a Contact");
            System.out.println("3. Delete an existing Contact");
            System.out.println("4. Update an existing Contact");   
            System.out.println("5. Display Contact List");
            System.out.println("6. Exit from program\n");

            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(sc.nextLine());
            switch(choice) {
                case 1: 
                    System.out.print("Enter contact Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter contact Number: ");
                    contactNo = sc.nextLine();
                    root = cb.createContact(root, name, contactNo);
                    break;
                
                case 2: 
                    System.out.print("Enter contact Name to search: ");
                    name = sc.nextLine();
                    TreeNode contact = cb.searchContact(root, name.toLowerCase());
                    if (contact != null) {
                        System.out.println("\nContact exists!! Details are: ");
                        System.out.println("Name: " + contact.contactName);
                        for (int i = 0; i < contact.contacts.length; i++) {
                            if (contact.contacts[i] != null) {
                                System.out.println("Contact Number " + (i+1) + ": " + contact.contacts[i]);
                            }
                        }
                        System.out.println();
                    } 
                    else {
                        System.out.println("Contact with this name doesn't exist\n");
                    }
                    break;
                
                case 3: 
                    System.out.print("Enter the contact Name which you want to delete: ");
                    name = sc.nextLine();
                    TreeNode deletedContact = cb.deleteContact(root, name.toLowerCase());
                    if (deletedContact != null)
                        System.out.println("Contact deleted");
                    else 
                        System.out.println("No contact exists with this name");
                    break;
                
                case 4:
                    System.out.print("Enter contact Name whose data is to be updated: ");
                    name = sc.nextLine();
                    contact = cb.searchContact(root, name.toLowerCase());
                    if (contact != null) {
                        cb.updateContact(contact);
                        System.out.println("Contact Updated");
                    } else {
                        System.out.println("No contact Exists");
                    }
                    break;
                    
                case 5:
                    cb.displayContact(root);
                    break;

                case 6:
                    System.exit(0);
                    break;

            }
        }
        // */
    }


}
