package contacts.backUp;

import java.io.*;
import java.lang.Record;
import java.util.ArrayList;
import java.util.List;

public class Phonebook {
    private List<AbstractRecord> contacts = new ArrayList<>();
    private File file;

    public Phonebook(String filepath) {
        // Verifica si filepath es nulo
        if (filepath == null) {
            file = new File("phonebook.db"); // Si no hay archivo especificado, usa un archivo por defecto
        } else {
            file = new File(filepath);
            load(); // Carga el archivo si existe
        }
    }

    private void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            contacts = (List<AbstractRecord>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Método para agregar un contacto
    public void addContact(AbstractRecord record) {
        contacts.add(record);
        save(); // Guardar los cambios después de agregar
    }

    public int count(){
        return contacts.size();
    }
}
