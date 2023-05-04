package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

import test_files.java.ZebraTest;

import java.util.ArrayList;
import java.util.Arrays;

public class ConstraintSolver {

    private Domain dom;
    private List<Variable> variableSet;
    private List<Constraint> constraintSet;

    public ConstraintSolver() {
        this.variableSet = new ArrayList<Variable>();
        this.constraintSet = new ArrayList<Constraint>();
    }

    public String toString() {
        //print variable
       // System.out.println("Variables and their Domains:\n");
       // for(int i = 0; i < variableSet.size(); i++)
          //  System.out.println(variableSet.get(i));
        //needs to print constraints as well

        //System.out.println("\nConstraints:\n");
       // for(int i = 0; i < constraintSet.size(); i++)
       //     System.out.println(constraintSet.get(i));
        return "";
    }

    private void parse(String fileName) {
        try {
            File inputFile = new File(fileName);
            Scanner scanner = new Scanner(inputFile);

            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();

                if(currentLine.startsWith("Domain-")) {
                    //this is our domain - i.e. a datastructure that contains values and can be updated, played with etc.
                    String s = currentLine.replace("Domain-","");
                    String[] array = s.split(","); 
                    int[] vals = new int[array.length];
                    for(int i = 0; i < array.length; i++) {
                        vals[i] = Integer.parseInt(array[i]);
                    }
                    dom = new Domain(vals);
                } else if (currentLine.startsWith("Var-")) {
                    //this is the code for every variable (a name and a domain)
                    String s = currentLine.replace("Var-","");
                    s = s.replaceAll("\\s+","");
                    Variable var = new Variable(s, dom); 
                    variableSet.add(var);
                } else if (currentLine.startsWith("Cons-")) {
                    //this is the code for the constraints
                    if (currentLine.startsWith("Cons-eqVPC")) {
                        String regexPattern = "\\(|\\)|\\ ";
                        String s = currentLine.replace("Cons-eqVPC(","").replaceAll(regexPattern, "");
                        String[] values = s.split("="); 
                        String[] values2 = values[1].split("\\+");
                        String val1Name = values[0];
                        String val2Name = values2[0];
                        Variable v1 = null;
                        Variable v2 = null;
                        for (Variable element : variableSet) {
                            if (element.hasThisName(val1Name)) {
                                v1 = element;
                            } else if(element.hasThisName(val2Name)) {
                                v2 = element; 
                            }
                        }
                        ConstraintEqualityVarPlusCons eq = new ConstraintEqualityVarPlusCons(v1, v2, Integer.parseInt(values2[1]), false);
                        constraintSet.add(eq);
                    }
                    else if (currentLine.startsWith("Cons-eqVV")) {
                        String regexPattern = "\\(|\\)|\\ ";
                        String s = currentLine.replace("Cons-eqVV(","").replaceAll(regexPattern, "");
                        String[] values = s.split("="); 
                        String val1Name = values[0];
                        String val2Name = values[1];
                        Variable v1 = null;
                        Variable v2 = null;
                        for (Variable element : variableSet) {
                            if (element.hasThisName(val1Name)) {
                                v1 = element;
                            } else if(element.hasThisName(val2Name)) {
                                v2 = element; 
                            }
                        }
                        ConstraintEqualityVarVar eq = new ConstraintEqualityVarVar(v1, v2);
                        constraintSet.add(eq);
                    }
                    else if (currentLine.startsWith("Cons-eqVC")) {
                        String regexPattern = "\\(|\\)|\\ ";
                        String s = currentLine.replace("Cons-eqVC(","").replaceAll(regexPattern, "");
                        String[] values = s.split("="); 
                        String val1Name = values[0];
                        Variable v1 = null;
                        for (Variable element : variableSet) {
                            if (element.hasThisName(val1Name)) {
                                v1 = element;
                            }
                        }
                        ConstraintEqualityVarCons eq = new ConstraintEqualityVarCons(v1, Integer.parseInt(values[1]));
                        constraintSet.add(eq);
                    }
                    else if (currentLine.startsWith("Cons-diff")) {
                        String regexPattern = "\\(|\\)|\\ ";
                        String s = currentLine.replace("Cons-diff(","").replaceAll(regexPattern, "");
                        String[] values = s.split(","); 
                        String val1Name = values[0];
                        String val2Name = values[1];
                        Variable v1 = null;
                        Variable v2 = null;
                        for (Variable element : variableSet) {
                            if (element.hasThisName(val1Name)) {
                                v1 = element;
                            } else if(element.hasThisName(val2Name)) {
                                v2 = element; 
                            }
                        }
                        ConstraintDifferenceVarVar eq = new ConstraintDifferenceVarVar(v1, v2);
                        constraintSet.add(eq);
                    }
                    else if (currentLine.startsWith("Cons-abs")) {
                        String regexPattern = "\\(|\\)|\\ ";
                        String s = currentLine.replace("Cons-abs(","").replaceAll(regexPattern, "");
                        String[] values = s.split("="); 
                        String[] values2 = values[0].split("\\-");
                        String val1Name = values2[0];
                        String val2Name = values2[1];
                        Variable v1 = null;
                        Variable v2 = null;
                        for (Variable element : variableSet) {
                            if (element.hasThisName(val1Name)) {
                                v1 = element;
                            } else if(element.hasThisName(val2Name)) {
                                v2 = element; 
                            }
                        }
                        ConstraintEqualityVarPlusCons eq = new ConstraintEqualityVarPlusCons(v1, v2, Integer.parseInt(values[1]), true);
                        constraintSet.add(eq);
                    }
                }

            }

            scanner.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Error.");
            e.printStackTrace();
        }
    }

    public void reduce() {
        boolean reduced = true;
        while(reduced){
            reduced = false;
            for(int i = 0; i < constraintSet.size(); i++){
                boolean result = constraintSet.get(i).reduce(); 
                if (result){
                    reduced = true;
                }
            }
        }
    }

    public boolean checkEmptyDomain(){
        for(int i = 0; i < variableSet.size(); i++){
            if (variableSet.get(i).d.isEmpty()){
                //System.out.println("Empty Domain found. The Subproblem is unsatisfiable");
                return true;
            }

        }
        return false;
    }

    public boolean isSolved(){
        boolean solved = true;
        for(int i = 0; i < variableSet.size(); i++){
            if(!(variableSet.get(i).d.isReducedToOnlyOneValue())){
                solved = false;
                break;
            }
        }
        return solved;
    }



    public void solveProblem(){
       // System.out.println("---------------------------------------------------------------------------------");
       // System.out.println("\nPROBLEM :\n");
        //System.out.println(this);
        Stack<List<Variable>> stk = new Stack<List<Variable>>();  
       //System.out.println("---------------------------------------------------------------------------------");
       // System.out.println("\nIteration 1 :\n");
        reduce();
        boolean sol = isSolved();
        if (sol){
           // System.out.println("Solution is found in 1 iteration. :- ");
                return;
        }
        boolean empt3 = checkEmptyDomain();
         if (empt3)
            return;
        sol = false;
       // System.out.println(d);
        stk.push(variableSet);
        
        int i = 2;
        while((stk.size()!=0) && !sol){ 
            
           // System.out.println("---------------------------------------------------------------------------------");
          //  System.out.println("\nIteration " + i + " :");
            
            // SPLITINNG DOMAIN

            List<Variable> old = new ArrayList<Variable>();
            old = stk.pop();
            int offset = 0;

            List<Variable> new1 = new ArrayList<Variable>();
            List<Variable> new2 = new ArrayList<Variable>();
            for (int j = 0; j < old.size(); j++ ){
                Variable var1 = new Variable(old.get(j).name,old.get(j).getDomain());
                Variable var2 = new Variable(old.get(j).name,old.get(j).getDomain());
                new1.add(var1);
                new2.add(var2);
            }
            stk.push(old);


            Variable split = dom.SmallestDomain(new1,offset);
            Variable split2 = dom.SmallestDomain(new2,offset);

            Domain[] domains = split.d.split();
            Domain[] domains2 = split2.d.split();

            split.setDomain(domains[0]);
            split2.setDomain(domains2[1]);

            //System.out.println("\nSplitting "+ split.name + " into " + domains[0].toString() + " and " + domains[1].toString()+"\n");
            //
            for (int j = 0; j < variableSet.size(); j++ ){
                variableSet.get(j).setDomain(new1.get(j).getDomain());
            } 
            reduce();
            sol = isSolved();
            if (sol){
                //System.out.println("Solution is found in "+ i + " iteration. :- \n");
                break;
            }
            System.out.println(this);
            boolean empt1 = checkEmptyDomain();
            if (!empt1)
                stk.push(new1);

          //  System.out.println("\n---------------------------------------------------------------------------------");
           
            for (int j = 0; j < variableSet.size(); j++ ){
                variableSet.get(j).setDomain(new2.get(j).getDomain());
            } 
            
            reduce();
            sol = isSolved();
            if (sol){
               // System.out.println("Solution is found in "+ i + " iteration. :- ");
                break;
            }
           // System.out.println(this);
 
            boolean empt2 = checkEmptyDomain();
            if (!empt2)
                stk.push(new2);

            if (empt1 && empt2){
                offset = 1;
            }
            
            i++;
        }
    }

    public static void main(String[] args) {
        
        ZebraTest Test1 = new ZebraTest();
        Test1.runTest();
    }

    public ArrayList<String> printAnswer(String string) {
        
        ArrayList<String> result = new ArrayList<>();
        parse("data.txt");
        solveProblem();
        for (int i = 0; i<variableSet.size(); i++){
            String ans = "Sol-" + variableSet.get(i).name + "-" + variableSet.get(i).d.toString();
            result.add(ans);
            System.out.println(ans);
        }
        return result;
    }

}
