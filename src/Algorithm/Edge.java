package Algorithm;

public class Edge implements Comparable<Edge>{ 
    private final int v;
    private final int w;
    private double weight;

    public Edge(int v, int w, double weight) {
        if (v < 0) throw new IllegalArgumentException("Vertex names must be nonnegative integers");
        if (w < 0) throw new IllegalArgumentException("Vertex names must be nonnegative integers");
        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    @Override
	public int compareTo(Edge that) {
		// TODO Auto-generated method stub
		if(this.weight < that.weight) {
			return -1;
		}
		else {
			if(this.weight > that.weight) {
				return 1;
			}
			else {
				return 0;
			}
		}
	}

	public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public double get_weight() {
        return weight;
    }

    public void set_weight(double weightValue) {
    	this.weight = weightValue;
    }
    public String toString() {
        return v + "->" + w + " " + String.format("%5.2f", weight);
    }
    

}
