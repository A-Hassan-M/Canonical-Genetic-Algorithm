import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

	private static void readFuzzySets(Scanner sc, int numOfFuzzySets, InputVariable variable) {
		sc.nextLine();
		FuzzySet fuzzySet;
		for (int i = 0; i < numOfFuzzySets; i++) {
			fuzzySet = new FuzzySet();
			fuzzySet.setType(sc.nextLine().split(" ")[0]);

			String[] fuzzySets = sc.nextLine().split(" ");
			// System.out.println(fuzzySets.toString());
			int in = 0;
			for (String s : fuzzySets) {
				int y = (in==0||in==fuzzySets.length-1 ?0:1);
				fuzzySet.getPoints().add(new Point(Float.valueOf(s),y));
				in++;
			}
			variable.fuzzySets.add(fuzzySet);
		}

	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("Input.txt"));
		int numOfVariables = sc.nextInt();
		Map<String,InputVariable> inputVariables = new HashMap<String,InputVariable>();
		ArrayList<Rule> rules = new ArrayList<>();
		for (int i = 0; i < numOfVariables; i++) {
			InputVariable variable = new InputVariable();

			variable.name = sc.next();
			variable.value = sc.nextFloat();

			int numOfFuzzySets = sc.nextInt();
			readFuzzySets(sc, numOfFuzzySets, variable);
			inputVariables.put(variable.name,variable);
		}
		InputVariable desiredVariable = new InputVariable();
		desiredVariable.name = sc.nextLine();
		int numOfFuzzySets = sc.nextInt();
//		System.out.println(desiredVariable.name + " " + numOfFuzzySets);
		readFuzzySets(sc, numOfFuzzySets, desiredVariable);
		int numOfRules = sc.nextInt();
//		System.out.println(numOfRules);
		for (int i = 0; i < numOfRules; i++) {
			int numOfOperands = sc.nextInt();
//			System.out.println(numOfOperands);
			Rule rule = new Rule();
			for (int j = 0; j < numOfOperands; j++) {
				if (j + 1 == numOfOperands)
					break;
				String operand1 = "";
				for (int k = 0; k < 3; k++)
					operand1 += sc.next() + " ";
//				System.out.println(operand1);
				rule.operands.add(operand1);
				String operation = sc.next();
				rule.operation.add(operation.equals("AND") ? Rule.Operations.AND : Rule.Operations.OR);
				String operand2 = "";
				for (int k = 0; k < 3; k++)
					operand2 += sc.next() + " ";
				rule.operands.add(operand2);
//				System.out.println(operand2);

			}
			sc.next();
			String output = "";
			for (int k = 0; k < 3; k++)
				output += sc.next() + " ";
			rule.output = output;
//			System.out.println(output);
			rules.add(rule);
		}
//		System.out.println("done");
		
		Fuzzy sol = new Fuzzy();
		for(InputVariable input:inputVariables.values()) {
			sol.fuzzify(input);
		}
		
		sol.InferenceOfRules(rules, inputVariables, desiredVariable);
		sol.defuzzify(desiredVariable);

		for (InputVariable input:inputVariables.values()) {
			System.out.println(input.name+" "+input.memberShip);
		}
		System.out.println(desiredVariable.name+" "+desiredVariable.memberShip);
		System.out.println(desiredVariable.value);
	}

}
/*
 * 2 position 10 2 Left trapezoidal 0 0 10 35 LeftCenter triangle 30 40 50 angel
 * -45 2 RBelow triangle -90 -45 9 RUpper triangle -9 23 54 firePosition 3
 * NegBig triangle -30 -30 -15 NegMed triangle -25 -15 -5 NegSm triangle -12 -6
 * 0 4 2 position = Left AND angel = RBelow then firePosition = PosSm 2 ANGEL =
 * RBelow OR position = LeftCenter then firePosition = PosMed 2 position =
 * Center AND angel = RBelow then firePosition = NegSm 2 position = Center OR
 * angel = LBelow then firePosition = NegMed
 */