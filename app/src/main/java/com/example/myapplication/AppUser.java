package com.example.myapplication;

public class AppUser  {
    //private long id;
    private Float Height;
    private Float Weight;
    private Float BmiScore;
    private String BmiClass;

    public AppUser() {
    }

    public AppUser(float Weight, float Height, float BmiScore, String BmiClass) {
        //this.id = id;
        this.Weight = Weight;
        this.Height = Height;
        this.BmiScore = BmiScore;
        this.BmiClass = BmiClass;
    }

    /*public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
*/
    public Float getHeight() {
        return Height;
    }

    public void setHeight(Float Height) {
        this.Height = Height;
    }

    public Float getWeight() {
        return Weight;
    }

    public void setWeight(Float Weight) {
        this.Weight = Weight;
    }

    public Float getBmiScore() {
        return BmiScore;
    }

    public void setBmiScore(Float BmiScore) {
        this.BmiScore = BmiScore;
    }

    public String getBmiClass() {
        return BmiClass;
    }

    public void setBmiClass(String BmiClass) {
        this.BmiClass = BmiClass;
    }


}
