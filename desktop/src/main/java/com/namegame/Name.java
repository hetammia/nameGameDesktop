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
    private boolean liked = false;

    //public Name(int id, String name, double mCount, double mFirst, double mOther, double fCount,
    //s            double fFirst, double fOther, double gRatio, double firstRatio) {
    public Name(int id, String name, double mCount, double fCount,
                double gRatio, double firstRatio, boolean filterOut, boolean liked) {
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
        this.liked = liked;
    }

    public Name(int id, String name, double allCount, double firstRatio, double genderRatio, boolean liked) {
        this.id = id;
        this.name = name;
        this.allCount = allCount;
        this.firstNameRatio = firstRatio;
        this.genderRatio = genderRatio;
        this.liked = liked;
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
    public void setLiked(boolean liked) {
        this.liked = liked;
    }
    public boolean getLiked() {
        return this.liked;
    }

}
