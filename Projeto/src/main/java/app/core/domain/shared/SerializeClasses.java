package app.core.domain.shared;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class used to serialize data (save to a file) and to deserialize data (load from file).
 *
 * @author Group 40
 */
public class SerializeClasses {

    /**
     * Method to serialize the data into a file.
     *
     * @param object   The list containing the data needed to be serialized.
     * @param fileName The name of the file where the data will be saved.
     * @param <E>      The type of the object passed via parameter.
     * @return true if the operation is successfully completed, or false otherwise.
     */
    public static <E> boolean saveObjectIntoDisk(List<E> object, String fileName) {

        try {

            FileOutputStream fileOut = new FileOutputStream("src/data/" + fileName);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOut);
            outputStream.writeObject(object);
            outputStream.close();
            fileOut.close();

        } catch (IOException ex) {
            return false;
        }

        return true;

    }

    /**
     * Method to deserialize the data present in a certain file.
     *
     * @param fileName The name of the file where the data is stored.
     * @param <E>      The type of the object passed via parameter.
     * @return A list containing the read data.
     */
    public static <E> List<E> loadObjectIntoMemory(String fileName) {
        List<E> list = new ArrayList<>();

        try {
            FileInputStream fileInputStream = new FileInputStream("src/data/" + fileName);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            list = (List<E>) inputStream.readObject();
            inputStream.close();
            fileInputStream.close();
        } catch (IOException ex) {
            //System.out.println("Erro");
        } catch (ClassNotFoundException ex) {
            //System.out.println("Classe erro");
        }

        return list;
    }

}
