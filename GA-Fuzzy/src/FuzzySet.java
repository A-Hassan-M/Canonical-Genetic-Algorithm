
import java.util.ArrayList;

public class FuzzySet {
	private String Type;
	private ArrayList<Point> points;

	public FuzzySet(String type, ArrayList<Point> points) {
		super();
		Type = type;
		this.points = points;
	}

	public FuzzySet() {
		points = new ArrayList<>();
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}

}
