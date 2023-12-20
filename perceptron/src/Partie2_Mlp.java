import donnees.ChargementData;
import donnees.Donnees;
import donnees.Imagette;
import mlp.HyperbolicTangent;
import mlp.MLP;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Partie2_Mlp
{
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
        int nbImg = 1000;

        // Charger les données
//        Donnees trainData = new ChargementData(NOMBRES_TRAIN_IMAGES, NOMBRES_TRAIN_LABELS, nbImg).donnees;
//        Donnees testData = new ChargementData(NOMBRES_T10K_IMAGES, NOMBRES_T10K_LABELS, nbImg).donnees;
        Donnees trainData = new ChargementData(FASHION_TRAIN_IMAGES, FASHION_TRAIN_LABELS, nbImg).donnees;
        Donnees testData = new ChargementData(FASHION_T10K_IMAGES, FASHION_T10K_LABELS, nbImg).donnees;


        // Créer un MLP avec la structure de couches souhaitée et le taux d'apprentissage
        int[] layers = {784, 9, 10}; // 784 neurones d'entrée (28x28 pixels), x neurones cachés, 10 neurones de sortie (classes 0-9)
        double learningRate = 0.1;
        MLP mlp = new MLP(layers, learningRate, new HyperbolicTangent()); //new mlp.Sigmoid());

        // Entraîner le MLP avec les données d'entraînement
        for (Imagette img : trainData.getImg()) {
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
            int predictedLabel = getLabelFromOutput(predictedOutputs);
            if (predictedLabel == img.getLabel()) {
                correctPredictions++;
            }
        }
        System.out.println("Accuracy: " + (double) correctPredictions / testData.getImg().size() * 100 + "%");
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
