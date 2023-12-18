import mlp.HyperbolicTangent;
import mlp.MLP;
import mlp.Sigmoid;

public class Partie1
{
    public static void main(String[] args) {
        double[][] andInputs = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        double[][] orInputs = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        double[][] xorInputs = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};

        double[] andOutputs = {0, 0, 0, 1};
        double[] orOutputs = {0, 1, 1, 1};
        double[] xorOutputs = {0, 1, 1, 0};

        int[] layers2 = {2, 2, 7, 1};
        int[] layers3 = {2, 2, 1};

        double learningRate = 0.6;

        MLP sigmoidAndPerceptron = new MLP(layers2, learningRate, new Sigmoid());
        MLP sigmoidOrPerceptron = new MLP(layers2, learningRate, new Sigmoid());
        MLP sigmoidXorPerceptron = new MLP(layers2, learningRate, new Sigmoid());

        MLP hyperbolicAndPerceptron = new MLP(layers2, learningRate, new HyperbolicTangent());
        MLP hyperbolicOrPerceptron = new MLP(layers2, learningRate, new HyperbolicTangent());
        MLP hyperbolicXorPerceptron = new MLP(layers2, learningRate, new HyperbolicTangent());


        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < andInputs.length; j++) {
                sigmoidAndPerceptron.backPropagate(andInputs[j], new double[]{andOutputs[j]});
                sigmoidOrPerceptron.backPropagate(orInputs[j], new double[]{orOutputs[j]});
                sigmoidXorPerceptron.backPropagate(xorInputs[j], new double[]{xorOutputs[j]});

                hyperbolicAndPerceptron.backPropagate(andInputs[j], new double[]{andOutputs[j]});
                hyperbolicOrPerceptron.backPropagate(orInputs[j], new double[]{orOutputs[j]});
                hyperbolicXorPerceptron.backPropagate(xorInputs[j], new double[]{xorOutputs[j]});
            }
        }

        for (int i = 0; i < andInputs.length; i++) {
            System.out.println("sigmoid AND(" + andInputs[i][0] + ", " + andInputs[i][1] + ") = " + sigmoidAndPerceptron.execute(andInputs[i])[0]);
            System.out.println("sigmoid OR(" + orInputs[i][0] + ", " + orInputs[i][1] + ") = " + sigmoidOrPerceptron.execute(orInputs[i])[0]);
            System.out.println("sigmoid XOR(" + xorInputs[i][0] + ", " + xorInputs[i][1] + ") = " + sigmoidXorPerceptron.execute(xorInputs[i])[0]);

            System.out.println("hyperbolic AND(" + andInputs[i][0] + ", " + andInputs[i][1] + ") = " + hyperbolicAndPerceptron.execute(andInputs[i])[0]);
            System.out.println("hyperbolic OR(" + orInputs[i][0] + ", " + orInputs[i][1] + ") = " + hyperbolicOrPerceptron.execute(orInputs[i])[0]);
            System.out.println("hyperbolic XOR(" + xorInputs[i][0] + ", " + xorInputs[i][1] + ") = " + hyperbolicXorPerceptron.execute(xorInputs[i])[0]);
        }

    }
}
