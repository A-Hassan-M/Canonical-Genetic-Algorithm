import java.util.ArrayList;

public class Rule {
    ArrayList<Operations> operation;
    ArrayList<String> operands;
    String output;

    Rule(){
        operation = new ArrayList<>();
        operands= new ArrayList<>();
        output= "";
    }


    enum Operations {
        AND,
        OR
    }
}
