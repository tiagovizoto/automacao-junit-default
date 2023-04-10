package br.com.quality.robots.support;

import com.google.common.base.Stopwatch;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Windows {
    public static boolean writeFile(String path, String body){
        try {
            FileWriter myWriter = new FileWriter(path);
            myWriter.write(body);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static java.util.List<String> listRunningProcesses() {
        java.util.List<String> processes = new ArrayList<String>();
        try {
            String line;
            Process p = Runtime.getRuntime().exec("tasklist.exe /fo csv /nh");
            BufferedReader input = new BufferedReader
                    (new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                if (!line.trim().equals("")) {
                    line = line.substring(1);
                    processes.add(line.substring(0, line.indexOf("\"")));
                }

            }
            input.close();
        }
        catch (Exception err) {
            err.printStackTrace();
        }
        return processes;
    }
    public static boolean isProcessActive(String processName){
        List<String> processes = listRunningProcesses();
        if (processes.contains(processName)) return true;
        return false;
    }
    public static boolean waitProcessFinish(String processName, int timeout) throws InterruptedException {
        int count = 0;
        long millis = 0;
        Stopwatch stopwatch = Stopwatch.createStarted();
        while (isProcessActive(processName)){
            if (count >= timeout) {
                System.out.println("Aguardou " + timeout + " Segundos e o processo: "+processName+" ainda estava executando");
                return false;
            }
            stopwatch.stop();
            millis = stopwatch.elapsed(TimeUnit.MILLISECONDS);
            if (millis > 1000) millis = 1000;
            Thread.sleep(1000 - millis);
            stopwatch.reset();
            stopwatch.start();
            count++;
        }
        return true;
    }
    public static boolean killProcessByName(String processName, boolean sysOutPrint){
        runCommand("taskkill /F /IM \""+processName+"\"", sysOutPrint);
        if (!isProcessActive(processName)) return true;
        return false;
    }

    public static void runCommand(String cmd, boolean sysOutPrint){
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
