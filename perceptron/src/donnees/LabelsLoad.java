package donnees;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class LabelsLoad {
    public int type;
    public int nb;
    public ArrayList<Integer> labels;
    public DataInputStream stream;
    public int limit;

    public LabelsLoad(String labelPath, int limit) throws IOException {
        File file = new File(labelPath);
        FileInputStream f = new FileInputStream(file);

        this.limit = limit;
        this.stream = new DataInputStream(f);
        this.type = stream.readInt();
        this.nb = stream.readInt();
        this.labels = new ArrayList<>();
        loadData();
    }

    public void loadData() throws IOException {
        for (int i = 0; i < limit; i++) {
            this.labels.add(stream.readUnsignedByte());
        }
    }

    public static void main(String[] args) throws IOException {
        LabelsLoad labels = new LabelsLoad("perceptron/data/nombres/train-labels.idx1-ubyte", 1000);
        System.out.println(labels.labels);

    }

}
