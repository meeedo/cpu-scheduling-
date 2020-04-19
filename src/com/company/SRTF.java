package com.company;

import java.util.ArrayList;
import java.util.Comparator;

public class SRTF {
    ArrayList<Process> processes;
    ArrayList<Process> readyq = new ArrayList<Process>();
    ArrayList<Process> run = new ArrayList<Process>();
    ArrayList<Process> brust = new ArrayList<Process>();
    ArrayList<Process> outputcoll = new ArrayList<Process>();

    SRTF(ArrayList<Process> processes,int context)
    {
        ArrayList<Process> tmp;
        this.processes = processes;

        for (int n = 0 ;n < processes.size() ; n++)
        {
            Process b = new Process();
            b.name = processes.get(n).name;
            b.arrivaltime = processes.get(n).arrivaltime;
            b.brusttime = processes.get(n).brusttime;
            b.waiting_time = processes.get(n).waiting_time;
            b.tern_around_time = processes.get(n).tern_around_time;
            brust.add(b);
        }

        Comparator<Process> comparatorarr =Comparator.comparing((Process p)-> p.arrivaltime);
        processes.sort(comparatorarr);

        Comparator<Process> comparatorbrs =Comparator.comparing((Process p)-> p.brusttime);

        int maxofarrival = processes.get(processes.size()-1).arrivaltime;
        int minofarrival = processes.get(0).arrivaltime;
        int size = 0;
        int lastProcess = 0;
        int start = minofarrival ;
        int finish = minofarrival +1 ;
        boolean flag = false;

        for(int i=minofarrival ; i<=maxofarrival ; i++ )
        {
            for (int j = 0 ; j < processes.size() ; j++)
            {

                if (i == processes.get(j).arrivaltime)
                {

                    readyq.add(processes.get(j));
                    readyq.sort(comparatorbrs);

                }

            }
            if (!(readyq.isEmpty())==true)
            {

                if( (readyq.get(0).brusttime > 0)) {
                    readyq.get(0).brusttime--;
                    Process test = new Process();
                    test.name=readyq.get(0).name;
                    test.arrivaltime=readyq.get(0).arrivaltime;
                    test.brusttime=readyq.get(0).brusttime;
                    test.color=readyq.get(0).color;
                    test.start_time=start;
                    test.finish_time=finish;
                    test.tern_around_time=readyq.get(0).tern_around_time;
                    test.waiting_time=readyq.get(0).waiting_time;
                    run.add(test);
                    size ++;

                }
                else
                {
                    readyq.remove(readyq.get(0));
                    readyq.sort(comparatorbrs);
                    start--;
                    finish--;

                }

            }
            start++;
            finish++;
        }
//-------------------------------------------------------------------------------------------------------------------------//
        for (int j = 0 ; j < readyq.size() ;j++)
        {

            for (int z = 0 ; z < readyq.get(j).brusttime ; z++)
            {
                Process test = new Process();
                test.name=readyq.get(j).name;
                test.arrivaltime=readyq.get(j).arrivaltime;
                test.brusttime=readyq.get(j).brusttime;
                test.color=readyq.get(j).color;
                test.start_time=start;
                test.finish_time=finish;
                test.tern_around_time=readyq.get(j).tern_around_time;
                test.waiting_time=readyq.get(j).waiting_time;
                run.add(test);
                start++;
                finish++;
                size ++;


            }
        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        for(int i=0 ; i<run.size() ; i++)
        {
            int saveI = i;
            if(i+1 <= run.size()-1)
            {
                if(run.get(i).name.equals(run.get(i+1).name))
                {
                    while(run.get(i).name.equals(run.get(i+1).name))
                    {
                        i += 1;
                        if(i+1 > run.size()-1) break;
                    }
                    run.get(saveI).finish_time = run.get(i).finish_time;
                    outputcoll.add(run.get(saveI));
                }
                else outputcoll.add(run.get(saveI));
            }
            else outputcoll.add(run.get(saveI));
        }
        System.out.println("------------------------------------//////////////////////////////////////////////////");
        for(int j = 0 ; j < outputcoll.size() ;j++)
        {

            System.out.println(outputcoll.get(j).name + "  "+outputcoll.get(j).start_time +" ----> " +outputcoll.get(j).finish_time  );

        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        for (int x=0;x<outputcoll.size();x++) {
            outputcoll.get(x).start_time += x * context;
            outputcoll.get(x).finish_time += x * context;
            outputcoll.get(x).tern_around_time = Double.parseDouble(Integer.toString(outputcoll.get(x).finish_time - outputcoll.get(x).arrivaltime));
            for (int i = 0; i < brust.size(); i++) {
                if (brust.get(i).name==outputcoll.get(x).name) {

                    outputcoll.get(x).waiting_time = outputcoll.get(x).tern_around_time - brust.get(i).brusttime;

                }
            }
        }

        System.out.println("------------------------------------//////////////////////////////////////////////////");
        for(int j = 0 ; j < outputcoll.size() ;j++)
        {

            System.out.println(outputcoll.get(j).name + "  "+outputcoll.get(j).start_time +" ----> " +outputcoll.get(j).finish_time  );

        }
//-------------------------------------------------------------------------------------------------------------------------------------
/////////////////////////////////////////////////////////////////////////////////////////////////-----///////////////////////
        Comparator<Process> comparatorname =Comparator.comparing((Process p)-> p.name);
        outputcoll.sort(comparatorname);
        double average_wating = 0.0;
        double average_tern = 0.0 ;
        for (int i=0;i<outputcoll.size();++i)
        {
            double wt = outputcoll.get(i).waiting_time;
            double tat = outputcoll.get(i).tern_around_time;
            if (i!=outputcoll.size()-1)
            {
                while (outputcoll.get(i).name.matches(outputcoll.get(i+1).name))
                {
                    wt = outputcoll.get(i+1).waiting_time;
                    tat = outputcoll.get(i+1).tern_around_time;
                    i++;
                    if (i == outputcoll.size()-1) {
                        break;
                    }
                }
            }
            average_wating += wt;
            average_tern += tat;
        }
        average_wating /= processes.size();
        average_tern /=processes.size();
//////////////////////////////////////////////////////////////////////////////////////////////////////-----/////////////////////
        System.out.println("------------------------------------//////////////////////////////////////////////////");
        System.out.println("\n\n\tST\tFT\tTAT\t\tWT\t");
        System.out.println(
                "============================================================================================");
        for (int i=0;i<outputcoll.size();i++)
        {

            System.out.println(outputcoll.get(i).name + "\t" +outputcoll.get(i).start_time + "\t" +outputcoll.get(i).finish_time
                    + "\t" +outputcoll.get(i).tern_around_time+ "\t\t" +outputcoll.get(i).waiting_time);
        }
        System.out.println("the average ternaround time  =    "+average_tern);
        System.out.println("the average waiting time  =    "+average_wating);
    }
}
