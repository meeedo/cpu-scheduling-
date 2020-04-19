package com.company;

import java.util.*;

class Process1 {
    String name;
    int BT, WT, AT, CT, TAT, remBT, priority;
    boolean flag;

    public Process1(String name, int burst, int AT, int PR) {
        this.name = name;
        BT = burst;
        this.AT = AT;
        WT = CT = TAT = 0;
        remBT = BT;
        priority = PR;
        flag = false;
    }

    public int getAT() {
        return AT;
    }

    public int getpriority() {
        return priority;
    }

    public void display() {
        System.out.println(name + "\t" + BT + "\t" + AT + "\t" + CT + "\t" + TAT + "\t" + WT + "\t" + priority);
    }
}

public class PriorityNonPreemptive {
    private Scanner sc;

    public Process1[] sortTmp(Process1 process[], int size, int time) {
        Vector<Process1> tmp = new Vector<Process1>();
        for (int i = 0; i < size; i++) {
            if (process[i].AT <= time && process[i].flag == false)
                tmp.add(process[i]);
        }
        Process1[] arr = new Process1[tmp.size()];
        for (int i = 0; i < tmp.size(); i++)
            arr[i] = tmp.get(i);
        Arrays.sort(arr, Comparator.comparing(Process1::getpriority));
        return arr;
    }

    public void execute(ArrayList<Process> processes, int context,int num) {

        int numProcess = num;
        Process1[] process = new Process1[numProcess];

        // taking input

        for (int i = 0; i < numProcess; i++) {
          //  System.out.println("P(" + (i + 1) + "):Enter  Burst time  & arrival time & priority"); //

            int bt = processes.get(i).brusttime;
            int at = processes.get(i).arrivaltime;
            int priority = processes.get(i).PPriority;
            process[i] = new Process1("P" + (i + 1), bt, at, priority);
            // Solution of Starvation problem by aging
            /*
             * if (priority>1) { priority --; }
             */
        }
        int time = 0;
        int sum = 0;

        // Sorting
        Arrays.sort(process, Comparator.comparing(Process1::getAT).thenComparing(Process1::getpriority));
        time = process[0].AT;
        Process1[] done = new Process1[numProcess];
        while (sum != numProcess) {
            Process1[] tmp;
            tmp = sortTmp(process, numProcess, time);
            if (tmp.length > 0) {
                sum += 1;
                tmp[0].CT = time + tmp[0].BT;
                time += tmp[0].BT;
                tmp[0].flag = true;
                done[sum - 1] = tmp[0];
                /// AGIng
                for (int i = 1; i < tmp.length; i++) {
                    if (tmp[i].priority >= 2)
                        tmp[i].priority -= 1;
                }
            } else if (tmp.length == 0 && sum != numProcess)
                time = process[sum].AT;
        }

        // Calculating waiting and Turnaround time

        double avgWT = 0, avgTAT = 0;
        System.out.println("\n\nPRNo\tBT\tAT\tCT\tTAT\tWT\tPR");
        System.out.println(
                "============================================================================================");

        for (int i = 0; i < numProcess; i++) {
            sum = process[i].CT = sum + process[i].BT;
            process[i].TAT = process[i].CT - process[i].AT;
            process[i].WT = process[i].TAT - process[i].BT;

            avgWT = avgWT + process[i].WT;
            avgTAT = avgTAT + process[i].TAT;

            done[i].display();
        }

        avgTAT = (double) avgTAT / numProcess;
        avgWT = (double) avgWT / numProcess;
        System.out.println("Average Waiting Time" + avgWT);
        System.out.println("Average TAT=" + avgTAT);

    }
}
//Class for sorting Processes

abstract class SortByPriority implements Comparator<Process> {

    public int compare(Process1 o1, Process1 o2) {

        return o1.priority - o2.priority;
    }

}