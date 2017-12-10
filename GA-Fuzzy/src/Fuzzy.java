import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Fuzzy {

	private double claculateY(Point p1, Point p2, double x) {
		double slope = (p2.y - p1.y) / (p2.x - p1.x);
		return slope * (x-p1.x) + p1.y;
	}

	public void fuzzify(InputVariable input) {
		double x = input.value;
		for (FuzzySet set : input.fuzzySets) {
			boolean z = true;
			for (int i = 0; i < set.getPoints().size() - 1; i++) {
				Point p1 = set.getPoints().get(i);
				Point p2 = set.getPoints().get(i + 1);
				if (x >= p1.x && x <= p2.x) {
					z = false;
					input.memberShip.put(set.getType(), claculateY(p1, p2, x));
					break;
				}
			}
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
				 oldRes = memberShip.get(rule.output.split(" = ")[1].trim());
			}catch(Exception ex){}
			double res = Double.valueOf(rule.operands.get(0));
			memberShip.put(rule.output.split(" = ")[1].trim(), res+oldRes);
		}
		out.memberShip = memberShip;
	}
	
	public void defuzzify(InputVariable out) {
		for (FuzzySet set : out.fuzzySets) {
			if(out.memberShip.get(set.getType())==null)
				out.memberShip.put(set.getType(),0.0);
		}
		double denominator = 0;
		double numerator = 0; 
		for(FuzzySet set:out.fuzzySets) {
			set.calcSentroid();
			numerator += out.memberShip.get(set.getType())*set.getCentroid();
			denominator += out.memberShip.get(set.getType());
		}
		
		out.value = numerator/denominator;
	}

	private void calcualte(Rule rule, Rule.Operations op, Map<String, InputVariable> variables) {
		for (int i = 0; i < rule.operation.size();) {
			if(rule.operation.get(i).equals(op)) {
				String operand1 = rule.operands.get(i);
				String operand2 = rule.operands.get(i+1);
				double val1;
				double val2;

				try {
					InputVariable var1 = variables.get(operand1.split(" = ")[0]);
					val1 = var1.memberShip.get(operand1.split(" = ")[1].trim());
				}catch(Exception ex) {
					val1 = Double.valueOf(operand1);
				}
				try {
					InputVariable var2 = variables.get(operand2.split(" = ")[0]);
					val2 = var2.memberShip.get(operand2.split(" = ")[1].trim());
				}catch(Exception ex) {
					val2 = Double.valueOf(operand2);
				}
				double res = op == Rule.Operations.AND?Math.min(val1,val2):Math.max(val1, val2);
				
				rule.operation.remove(i);
				rule.operands.remove(i);
				
				rule.operands.set(i, Double.toString(res));
			}else {i++;}
		}
	}

}
