package main.java;

public abstract class Constraint {

    Variable[] variables;
    

    public abstract String toString() ;
    

    protected abstract boolean isSatisfied() ;
       

    protected abstract boolean reduce() ;


}
