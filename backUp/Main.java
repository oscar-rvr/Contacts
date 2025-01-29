package contacts.backUp;

import java.lang.Record;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Verifica si se pasó un archivo como argumento
        String filePath = args.length > 0 ? args[0] : null;
        Phonebook phonebook = new Phonebook(filePath);

        // Crea la aplicación de contactos
        ContactsApp app = new ContactsApp(phonebook);

        // Ejecuta la aplicación
        app.run();
    }
}
