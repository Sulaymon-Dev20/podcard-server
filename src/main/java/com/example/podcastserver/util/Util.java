package com.example.podcastserver.util;

import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.net.URL;

@Log4j2
public class Util {
    public static final int VIA_STATION_POGRESHNOST = 150;

    public static String QR_CODE;
    public static String QR_CODE_TEST;

    static {
        try {
            QR_CODE_TEST = "http://" + new String(Runtime.getRuntime().exec("hostname -I").getInputStream().readAllBytes()).replaceAll("[ \n]", "") + "/api/scoreboard/";
            QR_CODE = "http://" + new BufferedReader(new InputStreamReader(new URL("https://checkip.amazonaws.com").openStream())).readLine().trim() + "/api/scoreboard/";
        } catch (Exception e) {
            QR_CODE = "Cannot get ip address";
        }
    }

    public static void modifyFile(String filePath, String oldString, String newString) {
        File fileToBeModified = new File(filePath);
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            String line = reader.readLine();
            while (line != null) {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }
            String newContent = oldContent.replaceAll(oldString, newString);
            writer = new FileWriter(fileToBeModified);
            writer.write(newContent);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    public static String cf(Integer number) {
        String a = number.toString();
        if (a.length() < 3) a = "0".repeat(3 - a.length()) + a;
        StringBuilder b = new StringBuilder("");
        for (char c : a.toCharArray()) {
            b.append(c).append(".");
        }
        return b.substring(0, b.lastIndexOf("."));
    }
}
