
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        TreeNode root = null;
        ContactBook cb = new ContactBook();
        
        int choice;
        String name, contactNo;
        System.out.println("CONTACT BOOK");

        // cb.searchContact(root, );
        
        // /* 
        while (true) {
            System.out.println("1. Create a New Contact");
            System.out.println("2. Search for a Contact");
            System.out.println("3. Delete an existing Contact");
            System.out.println("4. Display Contact List");
            System.out.println("5. Exit from program\n");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch(choice) {
                case 1: 
                    System.out.print("Enter contact Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter contact Number: ");
                    contactNo = sc.next();
                    root = cb.createContact(root, name, contactNo);
                    break;
                
                case 2: 
                    System.out.print("Enter contact Name to search: ");
                    name = sc.nextLine();
                    TreeNode contact = cb.searchContact(root, name);
                    if (contact != null) {
                        System.out.println("Contact exists!! Details are: ");
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
                
                case 4:
                    cb.displayContact(root);
                    break;

                case 5:
                    System.exit(0);

            }
        }
        // */
    }


}
