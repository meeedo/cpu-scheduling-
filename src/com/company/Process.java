package com.company;

import java.util.Comparator;

public class Process {
    String name;
    int brusttime;
    int arrivaltime;
    String color;
    int start_time=0;
    int finish_time=0;
    double tern_around_time=0;
    double waiting_time=0;
    public int PPriority =0;
    public int PQuantum = 0;
    public int Ag_Factor = 0;
    public int CMP_Time = 0;

    public Process(){
        name = null;
        brusttime = 0;
        arrivaltime = 0;
        String color = null;
        start_time=0;
        finish_time=0;
        tern_around_time=0;
        waiting_time=0;
        PPriority = 0;
        PQuantum = 0;
        Ag_Factor = 0;
    }

    public Process(String p, int pbt, int pat, int pp, int pq) {
        this.name = p;
        this.brusttime = pbt;
        this.arrivaltime = pat;
        this.PPriority = pp;
        this.PQuantum = pq;
        this.Ag_Factor = this.brusttime + this.arrivaltime + this.PPriority;
    }
    public static void print(Process tmp){
        System.out.println("Name: " + tmp.name);
        System.out.println("Factor: " + tmp.Ag_Factor);
        System.out.println("Arrival Time: " + tmp.arrivaltime);
        System.out.println("Burst Time: " + tmp.brusttime);
        System.out.println("Priority: " + tmp.PPriority);
        System.out.println("Quantum: " + tmp.PQuantum);
        System.out.println("\n");
    }
    public static Comparator<Process> ArrivalTime = new Comparator<Process>(){
        @Override
        public int compare(Process processes1, Process processes2) {
            return processes1.arrivaltime - processes2.arrivaltime;
        }
    };
/*
package pack;

public class Process {
    private String pName;
    private Float arrivalTime, burstTime;
    double startTime;
    private Integer priorityNum;
    private String color;


    public Process(String pName, float arrivalTime, float burstTime, Integer priorityNum, String color) {
        this.pName = pName;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priorityNum = priorityNum;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    String getpName() {
        return pName;
    }

     void setpName(String pName) {
        this.pName = pName;
    }

     Float getArrivalTime() {
        return arrivalTime;
    }

     void setArrivalTime(float arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

     float getBurstTime() {
        return burstTime;
    }

     void setBurstTime(float burstTime) {
        this.burstTime = burstTime;
    }

     Integer getPriorityNum() {
        return priorityNum;
    }

     void setPriorityNum(Integer priorityNum) {
        this.priorityNum = priorityNum;
    }

     Process() {
    }

    //return 0 for equal, -1 for smaller than, 1 for greater than
     int compareTo(Process p2) {
        return (this.getArrivalTime() < p2.getArrivalTime() ? -1 :
                (this.getArrivalTime().equals(p2.getArrivalTime()) ? 0 : 1));
    }

     void setStartTime(double startTime) {
        this.startTime = startTime;
    }

     private double getStartTime() {
        return this.startTime;

    }

    Double calcWaitingTime()
    {
        Double waitTime = this.getArrivalTime() + this.getStartTime();
        System.out.println("CalcWait:");
        System.out.println(this.pName +" arrival time: "+this.arrivalTime + ", StartTime: "+this.startTime);
        return (waitTime);
    }

    Double calcTurnAroundTime()
    {
        Double turnAroundTime = (this.getStartTime() + this.getBurstTime()) - this.getArrivalTime();
        System.out.println("CalcAround: ");
        System.out.println(this.pName +" start time: "+this.startTime + ", burst time: "+this.burstTime + ", arrival time: "+this.arrivalTime);

        return (turnAroundTime); //completion time - arrival time
    }


}

 */

}
