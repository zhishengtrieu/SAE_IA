public class Main
{
    public static void main(String[] args) {
        int[] layers = {2, 3, 1};
        double learningRate = 0.1;
        MLP mlpSig = new MLP(layers, learningRate, new Sigmoid());
        MLP mlpHyp = new MLP(layers, learningRate, new HyperbolicTangent());
    }
}
