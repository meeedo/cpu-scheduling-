package com.company;

import br.com.objectos.core.ArrayListMultimap;
import br.com.objectos.core.Multimap;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

import static java.lang.Thread.sleep;
import static sun.security.krb5.internal.LoginOptions.MAX;

public class SJF {

    SortedMap<Float, Process> processMap = new TreeMap<>();
    Process[] processArr;
    float currentTime;

    SJF (Process[] processArr, int arrSize) throws InterruptedException, FileNotFoundException {

        this.processArr = new Process[arrSize];
        this.processArr = processArr;

        //sorted according to arrival time

        for(int i =0; i< processArr.length; i++)
            processMap.put(processArr[i].getArrivalTime(), processArr[i]);

//        for(Float key: processMap.keySet())
//         System.out.println("SortedMap ("+key+"): " + processMap.get(key).getpName());
//


        executeMinBurstTime();
    }

    private void executeMinBurstTime() throws InterruptedException, FileNotFoundException {
        Process minBurstProcess;
        currentTime = 0;
        float overallWait = 0;
        while(processMap.size() > 0)
        {

            minBurstProcess = processMap.get(processMap.firstKey());
            for(float arrivalTime:processMap.keySet() )
            {
                if(processMap.get(arrivalTime).getBurstTime() <= minBurstProcess.getBurstTime() && arrivalTime <= currentTime)
                    minBurstProcess = processMap.get(arrivalTime);
            }
            execute(minBurstProcess);

            overallWait += minBurstProcess.calcWaitingTime();
            System.out.println(minBurstProcess.getpName() + " waiting time is: "+minBurstProcess.calcWaitingTime());
            System.out.println(minBurstProcess.getpName() + " turn around time is: "+minBurstProcess.calcTurnAroundTime());
            if(processMap.size() == 1)
                System.out.println("OverAll waiting time: " + overallWait);
        }
        System.out.println("");

    }

    private void execute(Process process) throws InterruptedException, FileNotFoundException {
        System.out.println("Process " + process.getpName() + " is being executed.");


        process.setStartTime(System.currentTimeMillis()*3600);
        sleep((long)process.getBurstTime()*3600);

        currentTime += ( process.getArrivalTime() > currentTime? process.getArrivalTime() : currentTime) + process.getBurstTime();

        System.out.println("Process "+process.getpName()+" is executed successfully.");

        System.out.println("current time = [" + currentTime + "]");


        processMap.remove(process.getArrivalTime(), process);


    }



}

