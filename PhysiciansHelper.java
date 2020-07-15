import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PhysiciansHelper<K,V> {
    private HashedDictionarySC<K,V> illnesses;
    private HashedDictionarySC<K,V> patients;
    final int ILLNESS_CAPACITY = 31;
    final int PATIENT_CAPACITY = 51;

    public PhysiciansHelper() {

    }

    public PhysiciansHelper(String filePath) {
        illnesses = new HashedDictionarySC<>(ILLNESS_CAPACITY);
        readIllnesses(filePath, illnesses);
        patients = new HashedDictionarySC<>(PATIENT_CAPACITY);
    }

    //adds the patient's name and symptoms, but not the illness. That one have to be implemented
    //seperately
    public void add(String name, String symptom) {
        patients.add((K) name, (V) symptom);
    }

    public void remove(String name, String symptom) {
        patients.remove((K) name, (V) symptom);
    }

    public void display() {
        //display the symptoms of the patient and his/her possible illnesses
        patients.displayHashTable();
    }
    private void readIllnesses(String filePath, HashedDictionarySC<K,V> list) {
        File f = new File(filePath);
        String value = "";
        String key = "";
        String line = "";
        try (Scanner input = new Scanner(f)) {
            while (input.hasNextLine()) {
                input.useDelimiter("\\r\\n");
                line = input.next();
                value = line;
                key = line;
                //filter original String to get value (illness)
                Scanner s1 = new Scanner(value);
                s1.useDelimiter(":");
                value =  s1.next();
                s1.close();
                //filter original String to get key (symptoms)
                Scanner s2 = new Scanner(key);
                s2.useDelimiter(":");
                key = s2.next();
                //tokenize each symptoms per illness and add it into hash table
                while (s2.hasNext()) {
                    s2.useDelimiter(",");
                    key = s2.next();
                    key = key.replaceAll(":", "");
                    key = key.trim();
                    list.add((K) key, (V) value);
                }
                s2.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
