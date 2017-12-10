
import java.util.ArrayList;

public class FuzzySet {
	private String Type;
	private ArrayList<Point> points;
	private double centroid;

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
	public void calcSentroid() {
		double A = 0;
		for(int i=0;i<points.size()-1;i++) {
			A +=points.get(i).x*points.get(i+1).y - points.get(i+1).x*points.get(i).y;
		}
		A/=2;
		for(int i=0;i<points.size()-1;i++) {
			double op1 = points.get(i).x*points.get(i+1).y - points.get(i+1).x*points.get(i).y;
			centroid +=(points.get(i).x+points.get(i+1).x)*op1;
		}
		centroid *=1/(6*A);
	}
	public double getCentroid() {
		return centroid;
	}

}
