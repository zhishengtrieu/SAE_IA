package donnees;

public class Imagette {
    public int[][] data;
    public int label;

    public Imagette(int[][] data) {
        this.data = data;
    }

    public int[][] getData() {
        return data;
    }

    public void setData(int[][] data) {
        this.data = data;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public int dist(Imagette img){
        int dist = 0;
        for (int i = 0; i < this.data.length; i++) {
            for (int j = 0; j < this.data[0].length; j++) {
                dist += Math.abs(this.data[i][j] - img.data[i][j]);
            }
        }
        return dist;
    }
}
