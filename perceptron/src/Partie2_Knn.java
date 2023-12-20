import classification.AlgoClassification;
import classification.kNN;
import donnees.ChargementData;
import donnees.Donnees;
import donnees.Imagette;

import java.io.IOException;

public class Partie2_Knn {
    /*
    --- Numbers MNIST dataset ---
     */
    public static final String NOMBRES_TRAIN_IMAGES = "perceptron/data/nombres/train-images.idx3-ubyte";
    public static final String NOMBRES_TRAIN_LABELS = "perceptron/data/nombres/train-labels.idx1-ubyte";
    public static final String NOMBRES_T10K_IMAGES = "perceptron/data/nombres/t10k-images.idx3-ubyte";
    public static final String NOMBRES_T10K_LABELS = "perceptron/data/nombres/t10k-labels.idx1-ubyte";

    /*
    --- Fashion MNIST dataset ---
     */
    public static final String FASHION_TRAIN_IMAGES = "perceptron/data/fashion/train-images-idx3-ubyte";
    public static final String FASHION_TRAIN_LABELS = "perceptron/data/fashion/train-labels-idx1-ubyte";
    public static final String FASHION_T10K_IMAGES = "perceptron/data/fashion/t10k-images-idx3-ubyte";
    public static final String FASHION_T10K_LABELS = "perceptron/data/fashion/t10k-labels-idx1-ubyte";


    public static void main(String[] args) throws IOException {
        AlgoClassification algo = new kNN(10);
        int nbImg = 1000;
//        Donnees testData = new ChargementData(NOMBRES_T10K_IMAGES, NOMBRES_T10K_LABELS, nbImg).donnees;
        Donnees testData = new ChargementData(FASHION_T10K_IMAGES, FASHION_T10K_LABELS, nbImg).donnees;
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
