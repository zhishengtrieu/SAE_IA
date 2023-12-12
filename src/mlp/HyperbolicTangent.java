package mlp;

public class HyperbolicTangent implements TransferFunction {
    @Override
    public double evaluate(double value) {
        return Math.tanh(value);
    }

    @Override
    public double evaluateDer(double value) {
        double tanh = evaluate(value);
        return 1 - tanh * tanh;
    }
}
