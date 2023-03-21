import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static final ArrayList<Record> trainingList = new ArrayList<>();
    public static final ArrayList<Record> testList = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        readData("iris_training.txt", trainingList);
        readData("iris_test.txt", testList);
        System.out.println("Podaj k (ilość sąsiadów): (lub -1 aby zakończyć)");
        int k = sc.nextInt();
        while (k != -1) {
            int accuracy = 0;
            if (k > trainingList.size()) System.out.println("Zbyt duże k");
            else if (k < -1) System.out.println("Zbyt małe k");
            else {
                for (Record record : testList)
                    if (find(record, k).equals(record.name)) accuracy++;
                System.out.println("Poprawnych: " + accuracy);
                System.out.println("Niepoprawnych: " + (testList.size() - accuracy));
                System.out.println("%: " + ((double) accuracy / testList.size()) * 100);
            }
            System.out.println("Podaj następne k lub -1 by zakończyć");
            k = sc.nextInt();
        }
    }

    public static void readData(String fileName, ArrayList<Record> list) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNext()) {
                list.add(new Record(scanner.nextLine()));
            }
        }
    }

    public static String find(Record n, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (Record record : trainingList) {
            map.put(record.name, 0);
        }
        trainingList.sort(new RecordComparator(n));
        for (int i = 0; i < k; i++) {
            map.put(trainingList.get(i).name, map.get(trainingList.get(i).name) + 1);
        }
        int max = 0;
        String ans = "";
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            if (max < e.getValue()) {
                max = e.getValue();
                ans = e.getKey();
            }
        }
        return ans;
    }
}