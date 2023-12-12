package partie2;

import partie2.algo.AlgoClassification;
import partie2.algo.kNN;
import partie2.donnees.ChargementData;
import partie2.donnees.Donnees;
import partie2.donnees.Imagette;

import java.io.IOException;

public class Statistiques {
    public static void main(String[] args) throws IOException {
        AlgoClassification algo = new kNN(10);
        Donnees testData = new ChargementData("data/t10k-images.idx3-ubyte", "data/t10k-labels.idx1-ubyte", 100).donnees;
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
