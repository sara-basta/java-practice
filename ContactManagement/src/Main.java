import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        ContactManager manager = new ContactManager();
        try {
            manager.loadFromFile("C:\\Users\\sarab\\Desktop\\output.txt");
        } catch (FileNotFoundException e) {
            System.out.println("No existing contacts found, starting fresh.");
        }

        boolean running = true;
        while (running) {
            int ch = displayMenu();
            switch(ch){
                case 1:
                    add(manager);
                    break;
                case 2:
                    try {
                        delete(manager);
                    } catch (ContactNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        update(manager);
                    } catch (ContactNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    search(manager);
                    break;
                case 5:
                    display(manager);
                    break;
                case 6:
                    manager.saveToFile("data/output.txt");
                    System.out.println("Contacts saved!");
                    break;
                case 7:
                    try {
                        manager.loadFromFile("data/output.txt");
                        System.out.println("Contacts loaded!");
                    } catch (FileNotFoundException e) {
                        System.out.println("No file found to load.");
                    }
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }

    static int displayMenu() {
        System.out.println("Menu:");
        System.out.println("1. Add");
        System.out.println("2. Delete");
        System.out.println("3. Update");
        System.out.println("4. Search");
        System.out.println("5. Display");
        System.out.println("6. Save");
        System.out.println("7. Load");
        System.out.print("Enter your choice: ");
        int c = scanner.nextInt();
        scanner.nextLine();
        return c;
    }

    static void add(ContactManager manager) {
        System.out.println("Enter the id : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the name : ");
        String name = scanner.nextLine();
        System.out.println("Enter the phone number : ");
        String phone = scanner.nextLine();
        System.out.println("Enter the email : ");
        String email = scanner.nextLine();

        Contact c = new Contact(id,name,phone,email);
        manager.addContact(c);
    }

    static void delete(ContactManager manager) {
        System.out.println("Enter the id : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        manager.deleteContact(id);
    }


    static void update(ContactManager manager) throws ContactNotFoundException {
        System.out.println("Entrez l’id du contact à modifier : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Nouveau nom : ");
        String name = scanner.nextLine();
        System.out.println("Nouveau numéro de téléphone : ");
        String phone = scanner.nextLine();
        System.out.println("Nouvel email : ");
        String email = scanner.nextLine();

        Contact newData = new Contact(id, name, phone, email);
        manager.updateContact(id, newData);
        System.out.println("Contact modifié !");
    }
    static void search(ContactManager manager) {
        System.out.println("Entrez le nom à rechercher : ");
        String name = scanner.nextLine();
        try {
            Contact result = manager.searchByName(name);
            System.out.println("Contact trouvé : " + result.getId() + ", " + result.getName() + ", " + result.getPhone() + ", " + result.getEmail());
        } catch (ContactNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    static void display(ContactManager manager) {
        System.out.println("Liste des contacts :");
        for (Contact c : manager.getContacts()) {
            System.out.println(c.getId() + ", " + c.getName() + ", " + c.getPhone() + ", " + c.getEmail());
        }
    }



}
