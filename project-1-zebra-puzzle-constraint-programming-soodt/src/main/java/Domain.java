package main.java;

import java.util.Arrays;
import java.util.List;

public class Domain {

    int[] vals;


    public Domain(int[] vals) {
        this.vals = vals;
    }


    public Domain(Domain d2) {
        //makes a copy of the domain from what d2 contains
        vals = new int[d2.vals.length];
        for(int i = 0; i < vals.length; i++)
            this.vals[i] = d2.vals[i];
    }

    public void delete(int index) {
        int[] newArr = new int[vals.length - 1]; // new array with one less element

        int j = 0; // index for new array
        for (int i = 0; i < vals.length; i++) {
            if (i != index) {
                newArr[j] = vals[i]; // copy element to new array
                j++; // increment index for new array
            }
        }

        this.vals = newArr;
    }


    /**
     * @return
     */
    public String toString() {
        String result  = "";
        for (int i = 0; i < vals.length; i++)
            result += vals[i];
        result += "";
        return result;
    }

    /**
     * @return
     */
    public Domain[] split() {

        Domain[] Domain_split = new Domain[2];
        int length = this.vals.length/2;
        int[] vals = new int[length];
        for (int i = 0; i<length; i++){
            vals[i] = this.vals[i];
        }
        Domain_split[0] = new Domain(vals);
        int[] vals2 = new int[this.vals.length - length];
        for (int i = 0; i<vals2.length; i++){
            vals2[i] = this.vals[i+length];
        }
        Domain_split[1] = new Domain(vals2);
        return (Domain_split);
    }

    /**
     * @return
     */
    public boolean isEmpty() {
        if (this.vals.length==0)
            return true;
        else
         return false;
    }

    /**
     * @return
     */
    public boolean equals(Domain d2) {

        if (Arrays.equals(this.vals, d2.vals))
            return true;
        else
             return false;
    }

    /**
     * @return
     */
    public boolean isReducedToOnlyOneValue() {

        if (this.vals.length == 1)
            return true;
        else
            return false;
    }

    /**
     * @return
     */
    public void printDomain(){
        String text = "Domain :";
        text += toString();
        System.out.println(text); 
    }

    public Variable SmallestDomain(List<Variable> variableSet, int offset){
        
        Variable larVar = variableSet.get(0);
        int larVarLength = larVar.d.vals.length;
        int length = variableSet.size();
        for (int i = 1; i< length; i++){
            if (larVarLength>variableSet.get(i).d.vals.length || larVarLength == 1){
                if (variableSet.get(i).d.vals.length>1){
                    larVar = variableSet.get(i-offset);
                }
            }
        }
        return larVar;
    }

}
