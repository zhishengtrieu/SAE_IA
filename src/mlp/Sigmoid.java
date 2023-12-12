package mlp;

public class Sigmoid implements TransferFunction {
    @Override
    public double evaluate(double value) {
        return 1.0 / (1.0 + Math.exp(-value));
    }

    @Override
    public double evaluateDer(double value) {
        double sigmoid = evaluate(value);
        return sigmoid * (1 - sigmoid);
    }
}