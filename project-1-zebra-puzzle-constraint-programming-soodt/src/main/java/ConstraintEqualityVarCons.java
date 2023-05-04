package main.java;

public class ConstraintEqualityVarCons extends Constraint {
    
    Variable v1;
    int cons;

    public ConstraintEqualityVarCons(Variable v1, int cons) {
        this.v1 = v1;
        this.cons = cons;
    }

    public String toString() {
        String result = "";
        result += "eqVC("+ this.v1.name + this.v1.d.toString() + " = " + this.cons + ")";
        return result;
    }

    protected boolean isSatisfied() {
        boolean result = false;
        for(int j = 0; j < this.v1.d.vals.length; j++) {
            if (this.v1.d.vals[j] == this.cons){
                result = true;
                break;
            }
        }
        return result;
    }

    protected boolean reduce() {
        boolean reduced = false;
        //if(isSatisfied()){
            for(int j = 0; j < this.v1.d.vals.length; j++) {
                if (this.v1.d.vals[j] != this.cons){
                    v1.d.delete(j); 
                    reduced = true;
                    j--;
                }
            }
       // }
        return reduced;
    }
}