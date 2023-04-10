package br.com.quality.robots.support;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecutorTask implements Runnable{
    String cmd = "";
    boolean sysOutPrint= false;
    public ExecutorTask(String command){
        this.cmd = command;
    }
    public ExecutorTask(String command, boolean sysOutPrint){
        this.sysOutPrint = sysOutPrint;
        this.cmd = command;
    }
    @Override
    public void run() {

        Process process = null;
        try {
            process = Runtime.getRuntime().exec(cmd);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line="";
            while ((line = reader.readLine()) != null) {
                if (sysOutPrint) System.out.println(line);
            }
            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            return;
        }
    }
}