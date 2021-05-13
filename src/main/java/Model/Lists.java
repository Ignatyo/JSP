package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Lists {

    private final LinkedHashMap<String, ArrayList<String>> headers;
    private static Lists instance;
    private final String filepath = "C:\\Users\\User\\IdeaProjects\\lab16\\src\\main\\java\\Model\\list.txt";

    private Lists() throws FileNotFoundException {
        headers = new LinkedHashMap<>();
        Scanner fileScanner = new Scanner(new File(filepath));
        while (fileScanner.hasNext()) {
            String fileString = fileScanner.nextLine();
            Scanner stringScanner = new Scanner(fileString);
            String listHeader = stringScanner.next();
            ArrayList<String> subHeadersList = new ArrayList<>();
            while (stringScanner.hasNext()) {
                subHeadersList.add(stringScanner.next());
            }
            headers.put(listHeader, subHeadersList);
        }
        fileScanner.close();
    }

    private void update() throws IOException {
        FileWriter writer = new FileWriter(new File(filepath));
        for (Map.Entry<String, ArrayList<String>> entry : headers.entrySet()) {
            writer.write(entry.getKey());
            writer.write(" ");
            for (String string : entry.getValue()) {
                writer.write(string);
                writer.write(" ");
            }
            writer.write("\n");
        }
        writer.flush();
        writer.close();
    }



    public void deleteFirst(String firstName) throws IOException {
        headers.remove(firstName);
        update();
    }

    public void deleteSecond(String firstName, String secondName) throws IOException {
        headers.get(firstName).remove(secondName);
        update();
    }

    public void addFirst(String firstName) throws IOException {
        headers.put(firstName, new ArrayList<>());
        update();
    }

    public void addSecond(String firstName, String secondName) throws IOException {
        headers.get(firstName).add(secondName);
        update();
    }

    public LinkedHashMap<String, ArrayList<String>> getList() {
        return new LinkedHashMap<>(headers);
    }

    public synchronized static Lists getInstance() throws FileNotFoundException {
        if (instance == null) {
            instance = new Lists();
        }
        return instance;
    }
}
