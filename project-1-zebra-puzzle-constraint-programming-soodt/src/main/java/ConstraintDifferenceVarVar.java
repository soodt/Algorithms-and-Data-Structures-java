package main.java;

public class ConstraintDifferenceVarVar extends Constraint {
    
    Variable v1, v2;

    public ConstraintDifferenceVarVar(Variable v1, Variable v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public String toString() {
        String result = "";
        result += "diff("+ this.v1.name + this.v1.d.toString() + " , " + this.v2.name + this.v2.d.toString() + ")";
        return result;
    }

    protected boolean isSatisfied() {
        if (this.v1.d.isReducedToOnlyOneValue() && this.v2.d.isReducedToOnlyOneValue() && this.v1.d.equals(this.v2.d)){
            return false;
        }
        else
            return true;
    }

    protected boolean reduce() {
        boolean reduced = false;
        //if(isSatisfied()){
            if(this.v2.d.isReducedToOnlyOneValue()){
                for(int j = 0; j < this.v1.d.vals.length; j++) {
                    if(this.v1.d.vals[j] == this.v2.d.vals[0]){
                        v1.d.delete(j);
                        reduced = true;
                        j--; 
                        break;
                    }
                }
            }
            if(this.v1.d.isReducedToOnlyOneValue()){
                for(int j = 0; j < this.v2.d.vals.length; j++) {
                    if(this.v2.d.vals[j] == this.v1.d.vals[0]){
                        v2.d.delete(j); 
                        reduced = true;
                        j--;
                        break;
                    }
                }
            }
       // }
        return reduced;
    }
}