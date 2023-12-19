import classification.AlgoClassification;
import classification.kNN;
import donnees.ChargementData;
import donnees.Donnees;
import donnees.Imagette;

import java.io.IOException;

public class Statistiques {
    public static void main(String[] args) throws IOException {
        AlgoClassification algo = new kNN(10);
        int nbImg = 1000;
        Donnees testData = new ChargementData("perceptron/data/t10k-images.idx3-ubyte", "perceptron/data/t10k-labels.idx1-ubyte", nbImg).donnees;
        int nbCorrect = 0;
        for (Imagette img : testData.getImg()) {
            if (algo.predict(img) == img.getLabel()) {
                nbCorrect++;
            }
        }
        System.out.println("Pourcentage de reussite : " + (nbCorrect * 100 / testData.getImg().size()) + "%");
        System.out.println("Nombre de reussite : " + nbCorrect);
        System.out.println("Nombre d'echec : " + (testData.getImg().size() - nbCorrect));
    }
}
