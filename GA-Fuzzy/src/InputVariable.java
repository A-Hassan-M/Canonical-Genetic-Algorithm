import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InputVariable {
	String name;
	double value;
	ArrayList<FuzzySet> fuzzySets;
	Map<String , Double> memberShip;
	InputVariable() {
		name = "";
		fuzzySets = new ArrayList<>();
		memberShip = new HashMap<String ,Double>();

	}

}
