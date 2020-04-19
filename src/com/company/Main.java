package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
	// write your code here
        System.out.println("enter the number of processes : ");
        Scanner scanner = new Scanner(System.in);
        int numberofprocess = scanner.nextInt();
        System.out.println("enter the context switch : ");
        int context=scanner.nextInt();
        ArrayList<Process> processes = new ArrayList<Process>();
        SRTF test;
        PriorityNonPreemptive pr=new PriorityNonPreemptive();
        SJF shortestJobFirst ;

        for (int i =0 ; i< numberofprocess ; i++)
        {
            Process process = new Process();

            System.out.println("enter the process name : ");
            process.name = scanner.next();

            System.out.println("enter the process arrival time : ");
            process.arrivaltime = scanner.nextInt();

            System.out.println("enter the process brust time : ");
            process.brusttime = scanner.nextInt();

            System.out.println("enter the process priority : ");
            process.PPriority = scanner.nextInt();

            processes.add(process);
        }
        /*
        for (int i =0 ; i< numberofprocess ; i++)
        {
            System.out.println(processes.get(i).name);
            System.out.println(processes.get(i).arrivaltime);
            System.out.println(processes.get(i).brusttime);
            System.out.println("///////////////////////////////");

        }

         */
        test = new SRTF(processes,context);
        System.out.println("?????????????????????????????????????????????????????????????????????????????????????????????");
        pr.execute(processes,context,numberofprocess);
        System.out.println("?????????????????????????????????????????????????????????????????????????????????????????????");
        shortestJobFirst = new SJF(processes, numberofprocess);

///----------------------------------------------------------------------------------------------------------------------------
      /*
        Scanner scanner2 = new Scanner(System.in);
        ArrayList<Process> processesArrayList = new ArrayList<>();
        int n;
        Process pr, pr1, pr2,pr3;
        int i = 0;
        System.out.println(" Enter NProcess: ");
        n = scanner2.nextInt();
        pr = new Process("P"+(i+1), 17, 0, 4, 4);
        pr1 = new Process("P"+(i+2), 6, 3, 9, 4);
        pr2 = new Process("P"+(i+3), 10, 4, 3, 4);
        pr3 = new Process("P"+(i+4), 4, 29, 8, 4);
        processesArrayList.add(pr);
        processesArrayList.add(pr1);
        processesArrayList.add(pr2);
        processesArrayList.add(pr3);
        AG_Scheduling ag_scheduling = new AG_Scheduling();
        ag_scheduling.Schedule();

       */
///----------------------------------------------------------------------------------------------------------------------------
        /*
        package pack;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        String pName, pcolor="";
        Float arrivalTime, burstTime;
        Integer priorityNum=0;

        System.out.println("Enter the number of processes: ");
        int pNum = Integer.parseInt(scanner.nextLine());
        Process[] processes = new Process[pNum];


        for (int i = 0; i < pNum; i++) {
            System.out.println("Enter name of (" + (i + 1) + "): ");
            pName = scanner.next();

            System.out.println("Enter arrival time of (" + (i + 1) + "): ");
            arrivalTime = scanner.nextFloat();

            System.out.println("Enter burst time of (" + (i + 1) + "): ");
            burstTime = scanner.nextFloat();



            Process process = new Process(pName, arrivalTime, burstTime, priorityNum, pcolor);
            processes[i] = process;
        }




        SJF shortestJobFirst = new SJF(processes, pNum);

    }
}

         */
///---------------------------------------------------------------------------------------------------------------------------


    }
}
