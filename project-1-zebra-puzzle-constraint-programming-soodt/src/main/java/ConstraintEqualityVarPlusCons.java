package main.java;

public class ConstraintEqualityVarPlusCons extends Constraint {
    
    Variable v1, v2;
    int cons;
    Boolean abs;

    public ConstraintEqualityVarPlusCons(Variable v1, Variable v2, int cons, Boolean abs){
        this.v1 = v1;
        this.v2 = v2;
        this.cons = cons;
        this.abs = abs;
    }

    public String toString() {
        String result = "";
        if(!abs)    
            result += "eqVPC("+ this.v1.name + this.v1.d.toString() + " = " + this.v2.name + this.v2.d.toString() + " + " + this.cons + ")";
        else
        result += "abs("+ this.v1.name + this.v1.d.toString() + " - " + this.v2.name + this.v2.d.toString() + " = " + this.cons + ")";

        return result;
    }

    protected boolean isSatisfied() {
        boolean result = false;
        for(int i = 0; i < this.v1.d.vals.length; i++) {
            if(!abs) {
                for(int j = 0; j < this.v2.d.vals.length; j++) { 
                    if(this.v1.d.vals[i] == this.v2.d.vals[j] + this.cons)
                        result = true;
                }
            }
            else {
                for(int j = 0; j < this.v2.d.vals.length; j++) {
                    if(Math.abs(this.v1.d.vals[i] - this.v2.d.vals[j]) == this.cons)
                        result = true;
                }
            }
        }
        return result;
    }

    protected boolean reduce() {

        boolean reduced = false;
       // if (isSatisfied()){
            //from d1
            for(int i = 0; i < this.v1.d.vals.length; i++) {
                Boolean flag = false;
                if(!abs) {
                    for(int j = 0; j < this.v2.d.vals.length; j++) { 
                        if(this.v1.d.vals[i] == this.v2.d.vals[j] + this.cons)
                            flag = true;
                    }
                    if (!flag) {
                        v1.d.delete(i);
                        reduced = true; 
                        i--;
                    }
                } else {
                    for(int j = 0; j < this.v2.d.vals.length; j++) {
                        if(Math.abs(this.v1.d.vals[i] - this.v2.d.vals[j]) == this.cons)
                        flag = true;
                    }
                    if (!flag) {
                        v1.d.delete(i); 
                        reduced = true;
                        i--;
                    }
                }
            }

            //from d2
            for(int i = 0; i < this.v2.d.vals.length; i++) {
                Boolean flag = false;
                if(!abs) {
                    for(int j = 0; j < this.v1.d.vals.length; j++) { 
                        if(this.v2.d.vals[i] == this.v1.d.vals[j] - this.cons)
                            flag = true;
                    }
                    if (!flag) {
                        v2.d.delete(i); 
                        reduced = true;
                        i--;
                    }
                } else {
                    for(int j = 0; j < this.v1.d.vals.length; j++) { 
                        if(Math.abs(this.v1.d.vals[j] - this.v2.d.vals[i]) == this.cons)
                            flag = true;
                    }
                    if (!flag) {
                        v2.d.delete(i); 
                        reduced = true;
                        i--;
                    }
                }
            }
      //  }
        return reduced;
    }

}