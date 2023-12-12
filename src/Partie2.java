import donnees.ChargementData;
import donnees.Donnees;
import donnees.Imagette;
import mlp.MLP;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Partie2
{
    public static void main(String[] args) throws IOException {
        // Charger les données d'entraînement
        List<Integer> trainLabels = manipulateDataLabels("data/train-labels.idx1-ubyte", 1000);
        List<Imagette> trainImagettes = manipulateDataImages("data/train-images.idx3-ubyte", trainLabels, 1000, true);

        // Créer un MLP avec la structure de couches souhaitée et le taux d'apprentissage
        int[] layers = {784, 100, 10}; // Par exemple, 784 neurones d'entrée (28x28 pixels), 100 neurones cachés, 10 neurones de sortie (classes 0-9)
        double learningRate = 0.1;
        MLP mlp = new MLP(layers, learningRate, new mlp.Sigmoid());

        // Entraîner le MLP avec les données d'entraînement
        for (Imagette img : trainImagettes) {
            double[] inputs = flattenAndNormalize(img.getData()); // Convertir le tableau 2D en tableau 1D et normaliser les valeurs
            double[] outputs = new double[10]; // Créer un tableau de sortie avec des zéros
            outputs[img.getLabel()] = 1; // Mettre à 1 l'index correspondant à l'étiquette de l'image

            mlp.backPropagate(inputs, outputs);
        }

        // Charger les données de test
        List<Integer> testLabels = manipulateDataLabels("data/t10k-labels.idx1-ubyte", 1000);
        List<Imagette> testImagettes = manipulateDataImages("data/t10k-images.idx3-ubyte", testLabels, 1000, false);

        // Tester le MLP avec les données de test
        int correctCount = 0;
        for (int i = 0; i < testImagettes.size(); i++) {
            double[] inputs = flattenAndNormalize(testImagettes.get(i).getData());
            double[] predictedOutputs = mlp.execute(inputs);
            int predictedLabel = getLabelFromOutput(predictedOutputs);
            if (predictedLabel == testLabels.get(i)) {
                correctCount++;
            }
        }
        System.out.println("Accuracy: " + (double) correctCount / testImagettes.size());
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

    private static int getLabelFromOutput(double[] output) {
        int label = 0;
        double maxOutput = output[0];
        for (int i = 1; i < output.length; i++) {
            if (output[i] > maxOutput) {
                label = i;
                maxOutput = output[i];
            }
        }
        return label;
    }

    private static List<Integer> manipulateDataLabels(String dataset, int nbImages) throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream(dataset));

        //lire numero magique
        int magicNumber = dis.readInt();

        //lire nb images
        int numberOfElements = dis.readInt();


        List<Integer> labels = new ArrayList<>();
        //pour chaque imagette
        for (int i = 0; i < nbImages; i++)
            //creer imagette
            labels.add(dis.readUnsignedByte());


        System.out.println(labels.get(0));
        System.out.println(labels.get(nbImages-1));

        //fermer flux
        dis.close();

        return labels;
    }

    private static List<Imagette> manipulateDataImages(String dataset, List<Integer> labels, int nbImages, boolean export) throws IOException {
        // open datainputstr
        DataInputStream dis = new DataInputStream(new FileInputStream(dataset));

        //lire numero magique
        int magicNumber = dis.readInt();

        //lire nb images
        int numberOfImages = dis.readInt();

        //cree tableau imagette
        int[][] pixels;

        //lire lignes
        int numberOfRows = dis.readInt();

        //lire col
        int numberOfColumns = dis.readInt();


        ArrayList<Imagette> imagettes = new ArrayList<>();
        //pour chaque imagette
        for (int i = 0; i < nbImages; i++) {
            //creer imagette
            pixels = new int[numberOfRows][numberOfColumns];

            //pour chaque ligne
            for (int row = 0; row < numberOfRows; row++)
                //pour chaque col
                for (int col = 0; col < numberOfRows; col++)
                    //lire octet (unsigned) readunsignedint
                    pixels[row][col] = dis.readUnsignedByte();

            //mettre dans imagette //ajoute tableau imagette
            imagettes.add(new Imagette(pixels));
        }


        //fermer flux
        dis.close();
        return imagettes;
    }
}
