import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Fuzzy {

	private double claculateY(Point p1, Point p2, double x) {
		double slope = (p2.y - p1.y) / (p2.x - p1.x);
		System.out.println("( "+p1.x +", "+p1.y+" )");
		System.out.println("( "+p2.x +", "+p2.y+" )");
		System.out.println(x+" & "+slope);
		return slope * (x-p1.x) + p1.y;
	}

	public void fuzzify(InputVariable input) {
		double x = input.value;
		for (FuzzySet set : input.fuzzySets) {
			boolean z = true;
			for (int i = 0; i < set.getPoints().size() - 1; i++) {
				Point p1 = set.getPoints().get(i);
				Point p2 = set.getPoints().get(i + 1);
				System.out.println(x);
				if (x >= p1.x && x <= p2.x) {
					z = false;
					input.memberShip.put(set.getType(), claculateY(p1, p2, x));
					break;
				}
			}
			System.out.println(set.getType());
			if (z)
				input.memberShip.put(set.getType(), 0.0);
		}
	}

	public void InferenceOfRules(ArrayList<Rule> rules, Map<String, InputVariable> variables, InputVariable out) {
		Map<String,Double> memberShip = new HashMap<>();
		for (Rule rule : rules) {
			
			calcualte(rule ,Rule.Operations.AND, variables);
			calcualte(rule ,Rule.Operations.OR, variables);
			double oldRes = 0;
			try {
				 oldRes = memberShip.get(rule.output);
			}catch(Exception ex){}
			double res = Double.valueOf(rule.operands.get(0));
			System.out.println(rule.output.split(" = ")[1].trim()+" "+res+" "+oldRes);
			memberShip.put(rule.output.split(" = ")[1].trim(), res+oldRes);
		}
		out.memberShip = memberShip;
	}

	private void calcualte(Rule rule, Rule.Operations op, Map<String, InputVariable> variables) {
		for (int i = 0; i < rule.operation.size(); i++) {
			if(rule.operation.get(i).equals(op)) {
				String operand1 = rule.operands.get(i);
				String operand2 = rule.operands.get(i+1);
				double val1 = -1;
				double val2 = -1;

				try {
					InputVariable var1 = variables.get(operand1.split(" = ")[0]);
					val1 = var1.memberShip.get(operand1.split(" = ")[1].trim());
					InputVariable var2 = variables.get(operand2.split(" = ")[0]);
					val2 = var2.memberShip.get(operand2.split(" = ")[1].trim());
					System.out.println(operand1+" "+operand2);
					System.out.println(val1+" "+val2);
				}catch(Exception ex) {
					val1 = (val1 != -1? val1: Double.valueOf(operand1));
					val2 = (val2 != -1? val2:Double.valueOf(operand2));
				}
				double res = Math.min(val1,val2);
				if(op == Rule.Operations.OR)
					res = Math.max(val1, val2);
				
				System.out.println("Res = "+res+" "+val1+" "+val2);
				rule.operation.remove(i);
				rule.operands.remove(i);
				
				rule.operands.set(i, Double.toString(res));
			}
		}
	}

}
