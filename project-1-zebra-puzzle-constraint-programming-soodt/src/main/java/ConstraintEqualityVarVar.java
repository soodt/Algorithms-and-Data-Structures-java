package main.java;

public class ConstraintEqualityVarVar extends Constraint {
    
    Variable v1, v2;

    public ConstraintEqualityVarVar(Variable v1, Variable v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public String toString() {
        String result = "eqVV(";
        result += this.v1.name + this.v1.d.toString()  + " = " + this.v2.name + this.v2.d.toString() + ")";
        return result;
    }

    protected boolean isSatisfied() {
        boolean common_d = false;
        for(int i = 0; i < this.v1.d.vals.length; i++) {
            for(int j = 0; j < this.v2.d.vals.length; j++) {
                if(this.v1.d.vals[i] == this.v2.d.vals[j]){
                    common_d = true;
                    break;
                }
            }
        }
        return common_d;
    }

    protected boolean reduce() {
        boolean reduced = false;
       // if(isSatisfied()){
            for(int i = 0; i < this.v1.d.vals.length; i++) {
                boolean common_d = false;
                for(int j = 0; j < this.v2.d.vals.length; j++) {
                    if(this.v1.d.vals[i] == this.v2.d.vals[j]){
                        common_d = true;
                        break;
                    }
                }
                if (!common_d){
                    v1.d.delete(i);
                    reduced = true;
                    i--;
                }
            }

            for(int i = 0; i < this.v2.d.vals.length; i++) {
                boolean common_d = false;
                for(int j = 0; j < this.v1.d.vals.length; j++) {
                    if(this.v2.d.vals[i] == this.v1.d.vals[j]){
                        common_d = true;
                        break;
                    }
                }
                if (!common_d){
                    v2.d.delete(i);
                    reduced = true;
                    i--;
                }
            }
       // }
        return reduced;
    }

}
