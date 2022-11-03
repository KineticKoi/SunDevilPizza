
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class FileManager {

    public static void saveCurrentCustomer() {
        try {
            Customer customerToSave = (Customer)SunDevilPizza.session.getUser();
            FileOutputStream fileout = new FileOutputStream(((Customer)customerToSave).getIDNum() + ".dat");
            ObjectOutputStream streamOut = new ObjectOutputStream(fileout);
            streamOut.writeObject(customerToSave);
            streamOut.close();
            fileout.close();
            System.out.println("Customer Saved Successfully");
        } catch(NotSerializableException n) {
            System.out.println("Not serializable exception\n");
        } catch(IOException e) {
            System.out.println("Data file read exception\n");
        }
    }
    
    public static Customer loadCustomer(int asuriteID) {
        try {
            FileInputStream fileIn = new FileInputStream(String.valueOf(asuriteID) + ".dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Customer customer = (Customer)in.readObject();
            System.out.println("Customer save found and read");
            return customer;
        } catch(ClassNotFoundException c) {
           System.out.println("Class not found\n");    
        } catch (FileNotFoundException f) {
            System.out.println("No customer save exists\n");
        } catch(NotSerializableException n) {
            System.out.println("Not serializable exception\n");
        } catch(IOException e) {
           System.out.println("Data file read exception\n");
        }
        return null;
    }
    public static boolean existingCustomer(String filepath) {
        File f = new File(filepath);
        if (f.exists()) {
            return true;
        }
        return false;
    }
}
