package classification;

import donnees.ChargementData;
import donnees.Donnees;
import donnees.Imagette;

import java.io.IOException;

public abstract class AlgoClassification {
    protected Donnees trainData;

    public AlgoClassification() throws IOException {
        this.trainData = new ChargementData("data/train-images.idx3-ubyte", "data/train-labels.idx1-ubyte", 1000).donnees;
    }

    public abstract int predict(Imagette img);

}
