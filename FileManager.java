import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//Class 
public class FileManager {

    //Method 
    public static void saveCustomer(Customer customer) {
        try {
            FileOutputStream fileout = new FileOutputStream(SunDevilPizza.customerFilesPath + customer.getIDNum() + ".dat"); //
            ObjectOutputStream streamOut = new ObjectOutputStream(fileout); //
            streamOut.writeObject(customer); //Writing object to out file stream
            streamOut.close(); //Closing out file stream
            fileout.close(); //Closing out file
        } catch(NotSerializableException n) {
            System.out.println("Not serializable exception\n"); 
        } catch(IOException e) {
            System.out.println("Data file read exception\n");
        }
    }
    
    //Method for getting Customer object from ASURITE ID
    public static Customer loadCustomer(String asuriteID) {
        try {
            // Attempt to retrieve Customer object
            FileInputStream fileIn = new FileInputStream(SunDevilPizza.customerFilesPath + asuriteID + ".dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Customer customer = (Customer)in.readObject();
            return customer;
        } catch(ClassNotFoundException c) {  // Handle class not found exceptions
           System.out.println("Class not found\n");    
        } catch (FileNotFoundException f) {  // Handle file not found exceptions
            System.out.println("No customer save exists\n");
        } catch(NotSerializableException n) {  // Handle non serializable data
            System.out.println("Not serializable exception\n");
        } catch(IOException e) {  // Handle IO exceptions
           System.out.println("Data file read exception\n");
        }
        return null;
    }
    
    //Method for checking if a customer already exists
    public static boolean existingCustomer(String asuriteID) {
        // Get the customer file and return if it exists
        File f = new File(SunDevilPizza.customerFilesPath + asuriteID + ".dat");
        return f.exists();
    }
    
} //End of FileManager class

