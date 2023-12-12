import mlp.MLP;
import donnees.ChargementData;
import donnees.Donnees;
import donnees.Imagette;

import java.io.IOException;

public class Partie2 {
    public static void main(String[] args) throws IOException {
        // Charger les données
        Donnees trainData = new ChargementData("data/train-images.idx3-ubyte", "data/train-labels.idx1-ubyte", 1000).donnees;
        Donnees testData = new ChargementData("data/t10k-images.idx3-ubyte", "data/t10k-labels.idx1-ubyte", 1000).donnees;

        // Créer un MLP avec la structure de couches souhaitée et le taux d'apprentissage
        int[] layers = {784, 100, 10}; // Par exemple, 784 neurones d'entrée (28x28 pixels), 100 neurones cachés, 10 neurones de sortie (classes 0-9)
        double learningRate = 0.1;
        MLP mlp = new MLP(layers, learningRate, new mlp.Sigmoid());

        // Entraîner le MLP avec les données de test
        for (Imagette img : testData.getImg()) {
            double[] inputs = flattenAndNormalize(img.getData()); // Convertir le tableau 2D en tableau 1D et normaliser les valeurs
            double[] outputs = new double[10]; // Créer un tableau de sortie avec des zéros
            outputs[img.getLabel()] = 1; // Mettre à 1 l'index correspondant à l'étiquette de l'image

            mlp.backPropagate(inputs, outputs);
        }

        // Testez votre MLP avec les données de test
        int correctPredictions = 0;
        for (Imagette img : testData.getImg()) {
            double[] inputs = flattenAndNormalize(img.getData()); // Convertir le tableau 2D en tableau 1D et normaliser les valeurs
            double[] predictedOutputs = mlp.execute(inputs);
            int predictedLabel = getMaxIndex(predictedOutputs);
            if (predictedLabel == img.getLabel()) {
                correctPredictions++;
            }
        }

        double accuracy = (double) correctPredictions / testData.getImg().size();
        System.out.println("Accuracy: " + accuracy);
    }

    private static double[] flattenAndNormalize(int[][] data) {
        double[] flatData = new double[data.length * data[0].length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                flatData[i * data[i].length + j] = data[i][j] / 255.0; // Normaliser les valeurs à l'échelle [0, 1]
            }
        }
        return flatData;
    }

    private static int getMaxIndex(double[] array) {
        int maxIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
