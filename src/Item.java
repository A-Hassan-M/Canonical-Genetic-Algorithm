
public class Item {
	private int weight;
	private int value;

	public Item(int w, int v) {
		weight = w;
		value = v;
	}

	// Setters
	public void setValue(int value) {
		this.value = value;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	// Getters
	public int getValue() {
		return value;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return "(Weight = " + weight + ", Value = " + value + ")";
	}

}
