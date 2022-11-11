import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// class to manage file assets
public class FileManager
{
    // saving the customer locally to the administrator's computer
    public static void saveCustomer(Customer customer) {
        // customer class contains order information too
        try
        {
            // creating a new data file to hold output for the specific customer
            FileOutputStream fileout = new FileOutputStream(SunDevilPizza.customerFilesPath + customer.getIDNum() + ".dat");
            // creating a new object data stream
            ObjectOutputStream streamOut = new ObjectOutputStream(fileout);
            // writing customer data to the output
            streamOut.writeObject(customer);
            streamOut.close();
            // closing stream
            fileout.close();
        }
        // printing exceptions to console log
        catch(NotSerializableException n)
        {
            System.out.println("Not serializable exception\n");
        }
        catch(IOException e)
        {
            System.out.println("Data file read exception\n");
        }
    }
    
    // loading customers from the data file
    public static Customer loadCustomer(String asuriteID)
    {
        try
        {
            // reading data file for the customer if it exists
            FileInputStream fileIn = new FileInputStream(SunDevilPizza.customerFilesPath + asuriteID + ".dat");
            // creating a data stream
            ObjectInputStream in = new ObjectInputStream(fileIn);
            // reading the file to see if the customer is present
            Customer customer = (Customer)in.readObject();
            return customer;
        }
        // if the customer class is not found
        catch(ClassNotFoundException c)
        {
           System.out.println("Class not found\n");    
        }
        // if the specific customer does not exist
        catch (FileNotFoundException f)
        {
            System.out.println("No customer save exists\n");
        }
        // not serializable or data exception
        catch(NotSerializableException n)
        {
            System.out.println("Not serializable exception\n");
        }
        catch(IOException e)
        {
           System.out.println("Data file read exception\n");
        }
        return null;
    }
    
    // if the customer is already existing
    public static boolean existingCustomer(String asuriteID)
    {
        // creating a new file for the customer
        File f = new File(SunDevilPizza.customerFilesPath + asuriteID + ".dat");
        // return file exists
        return f.exists();
    }
}