import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.io.IOException;

public class ContactManager {
    private List<Contact> contacts = new ArrayList<>();

    public List<Contact> getContacts() {
        return Collections.unmodifiableList(contacts);
    }

    public void addContact(Contact c) {
        if(c==null) {
            throw new NullPointerException("Error, contact cannot be null");

        }
        contacts.add(c);
    }

    public void deleteContact(int id) throws ContactNotFoundException{
        boolean found = false;
        Iterator<Contact> it = contacts.iterator();
        while(it.hasNext()){
            Contact c = it.next();
            if (c.getId() == id) {
                it.remove();
                found = true;
                break;
            }
        }

        if (!found) {
            throw new ContactNotFoundException("Contact with id " + id + " not found");
        }
    }


    public void updateContact(int id, Contact newData){
        Iterator<Contact> it = contacts.iterator();
        boolean found = false;

        while(it.hasNext()) {
            Contact c = it.next();
            if (c.getId()==id) {
                c.setName(newData.getName());
                c.setPhone(newData.getPhone());
                c.setEmail(newData.getEmail());
                found = true;
                break;
            }
        }

        if (!found) {
            throw new ContactNotFoundException("Contact not found!");
        }
    }

    public Contact searchByName(String name) throws ContactNotFoundException{
        for (int i=0; i<contacts.size();i++) {
            if(contacts.get(i).getName().equals(name)){
                return contacts.get(i);
            }
        }
        throw new ContactNotFoundException("Contact not found!");

    }

    public void saveToFile(String filename){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename)))
        {
            writer.write("id,name,phone,email");
            writer.newLine();

            for (Contact c : contacts) {
                writer.write(c.getId() + "," + c.getName() + "," + c.getPhone() + "," + c.getEmail());
                writer.newLine();
            }

        }

         catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void loadFromFile(String filename)throws FileNotFoundException{
        try (BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            contacts.clear();
            String line;
            // we use readLine to skip the first line cause it contains the column names
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String fields[]= line.split(",");
                int id = Integer.parseInt(fields[0]);
                String name = fields[1];
                String phone = fields[2];
                String email = fields[3];
                contacts.add(new Contact(id, name, phone, email));

            }
        }
        catch(IOException e ) {
            e.printStackTrace();
        }
    }



}
