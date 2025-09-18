package com.namegame;

public class Name {
    public int id;
    public String name;
    private double maleCount;
    //private double maleFirst;
    //private double maleOther;
    private double femaleCount;
    private double allCount;
    //private double femaleFirst;
    //private double femaleOther;
    private double genderRatio;
    private double firstNameRatio;
    private boolean filterOut;

    //public Name(int id, String name, double mCount, double mFirst, double mOther, double fCount,
    //s            double fFirst, double fOther, double gRatio, double firstRatio) {
    public Name(int id, String name, double mCount, double fCount,
                double gRatio, double firstRatio, boolean filterOut) {
        this.id = id;
        this.name = name;
        this.maleCount = mCount;
        //this.maleFirst = mFirst;
        //this.maleOther = mOther;
        this.femaleCount = fCount;
        //this.femaleFirst = fFirst;
        //sthis.femaleOther = fOther;
        this.genderRatio = gRatio;
        this.firstNameRatio = firstRatio;
        this.filterOut = filterOut;
    }

    public Name(int id, String name, double allCount, double firstRatio, double genderRatio) {
        this.id = id;
        this.name = name;
        this.allCount = allCount;
        this.firstNameRatio = firstRatio;
        this.genderRatio = genderRatio;
    }

    public double getAllCount() {
        return this.femaleCount + this.maleCount;
    }

    public boolean isFiltered() {
        return this.filterOut;
    }

    public double getFirstNameRatio() {
        return this.firstNameRatio;
    }

    public double getGenderRatio() {
        return this.genderRatio;
    }

}
