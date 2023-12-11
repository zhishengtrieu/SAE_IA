public class Main
{
    public static void main(String[] args) {
        double[][] andInputs = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        double[][] orInputs = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        double[][] xorInputs = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};

        double[] andOutputs = {0, 0, 0, 1};
        double[] orOutputs = {0, 1, 1, 1};
        double[] xorOutputs = {0, 1, 1, 0};

//        MLP andPerceptron = new MLP(new int[]{2, 2, 1}, 0.6, new Sigmoid());
//        MLP orPerceptron = new MLP(new int[]{2, 2, 1}, 0.6, new Sigmoid());
//        MLP xorPerceptron = new MLP(new int[]{2, 2, 1}, 0.6, new Sigmoid());

        MLP andPerceptron = new MLP(new int[]{2, 2, 1}, 0.6, new HyperbolicTangent());
        MLP orPerceptron = new MLP(new int[]{2, 2, 1}, 0.6, new HyperbolicTangent());
        MLP xorPerceptron = new MLP(new int[]{2, 2, 1}, 0.6, new HyperbolicTangent());


        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < andInputs.length; j++) {
                andPerceptron.backPropagate(andInputs[j], new double[]{andOutputs[j]});
                orPerceptron.backPropagate(orInputs[j], new double[]{orOutputs[j]});
                xorPerceptron.backPropagate(xorInputs[j], new double[]{xorOutputs[j]});
            }
        }

        for (int i = 0; i < andInputs.length; i++) {
            System.out.println("AND(" + andInputs[i][0] + ", " + andInputs[i][1] + ") = " + andPerceptron.execute(andInputs[i])[0]);
            System.out.println("OR(" + orInputs[i][0] + ", " + orInputs[i][1] + ") = " + orPerceptron.execute(orInputs[i])[0]);
            System.out.println("XOR(" + xorInputs[i][0] + ", " + xorInputs[i][1] + ") = " + xorPerceptron.execute(xorInputs[i])[0]);
        }

    }
}
