package com.yugesh.app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class App {
    public static void main(String[] args) {
        // Gathering system information
        String osName = System.getProperty("os.name");
        String nodeName = System.getenv("COMPUTERNAME");
        String osRelease = System.getProperty("os.version");
        String osVersion = osRelease; // In Windows, release and version are often similar
        String machine = System.getProperty("os.arch");
        String processor = System.getProperty("os.arch") + ", " + System.getenv("PROCESSOR_IDENTIFIER");

        // Getting current date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = now.format(formatter);

        // Creating the log file name with timestamp
        DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = now.format(fileFormatter);
        String fileName = "F:\\sys_info\\output_" + timestamp + ".log";

        // Building the system information string
        StringBuilder sysInfo = new StringBuilder();
        sysInfo.append("---- System Information ----\n");
        sysInfo.append("System      : ").append(osName).append("\n");
        sysInfo.append("Node Name   : ").append(nodeName).append("\n");
        sysInfo.append("Release     : ").append(osRelease).append("\n");
        sysInfo.append("Version     : ").append(osVersion).append("\n");
        sysInfo.append("Machine     : ").append(machine).append("\n");
        sysInfo.append("Processor   : ").append(processor).append("\n");
        sysInfo.append("----------------------------\n");
        sysInfo.append("Current Date & Time: ").append(currentDateTime).append("\n");

        // Writing to the file
        try {
            File dir = new File("F:\\sys_info");
            if (!dir.exists()) {
                dir.mkdirs(); // Create directory if it doesn't exist
            }
            try (FileWriter writer = new FileWriter(fileName)) {
                writer.write(sysInfo.toString());
                System.out.println("System information written to " + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}