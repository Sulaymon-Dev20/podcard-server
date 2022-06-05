package com.example.podcastserver.util;

import java.lang.management.ManagementFactory;
import java.time.LocalDate;
import java.time.LocalTime;

import static com.example.podcastserver.util.ColorsTerminal.*;

public class Logger {
    static final String PID = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];

    public static void info(Exception exception) {
        System.out.println(ANSI_BG_BLACK + LocalDate.now() + " " + LocalTime.now().toString().substring(0, 12) + ANSI_BLUE + "  INFO " + ANSI_PURPLE + PID + ANSI_RESET + ANSI_BG_BLACK + " --- [127.0.0.1 Log  ] " + ANSI_CYAN + "com.sulaymon.yahyo.alhamdulillah         " + ANSI_RESET + ANSI_BG_BLACK + ": " + ANSI_BG_GREEN + exception.getMessage().replace("\n", " ") + ANSI_RESET);
    }

    public static void error(Exception exception) {
        System.out.println(ANSI_BG_BLACK + LocalDate.now() + " " + LocalTime.now().toString().substring(0, 12) + ANSI_RED + " ERROR " + ANSI_PURPLE + PID + ANSI_RESET + ANSI_BG_BLACK + " --- [127.0.0.1 Log  ] " + ANSI_CYAN + "com.sulaymon.yahyo.alhamdulillah         " + ANSI_RESET + ANSI_BG_BLACK + ": " + ANSI_BG_BLACK + ANSI_RED + exception.getMessage().replace("\n", " ") + ANSI_RESET);
    }

    public static void warning(Exception exception) {
        System.out.println(ANSI_BG_BLACK + LocalDate.now() + " " + LocalTime.now().toString().substring(0, 12) + ANSI_BLUE + "  INFO " + ANSI_PURPLE + PID + ANSI_RESET + ANSI_BG_BLACK + " --- [127.0.0.1 Log  ] " + ANSI_CYAN + "com.sulaymon.yahyo.alhamdulillah         " + ANSI_RESET + ANSI_BG_BLACK + ": " + ANSI_BG_GREEN + exception.getMessage().replace("\n", " ") + ANSI_RESET);
    }

    public static void info(String message) {
        System.out.println(ANSI_BG_BLACK + LocalDate.now() + " " + LocalTime.now().toString().substring(0, 12) + ANSI_BLUE + "  INFO " + ANSI_PURPLE + PID + ANSI_RESET + ANSI_BG_BLACK + " --- [127.0.0.1 Log  ] " + ANSI_CYAN + "com.sulaymon.yahyo.alhamdulillah         " + ANSI_RESET + ANSI_BG_BLACK + ": " + ANSI_BG_GREEN + message.replace("\n", " ") + ANSI_RESET);
    }

    public static void error(String message) {
        System.out.println(ANSI_BG_BLACK + LocalDate.now() + " " + LocalTime.now().toString().substring(0, 12) + ANSI_RED + " ERROR " + ANSI_PURPLE + PID + ANSI_RESET + ANSI_BG_BLACK + " --- [127.0.0.1 Log  ] " + ANSI_CYAN + "com.sulaymon.yahyo.alhamdulillah         " + ANSI_RESET + ANSI_BG_BLACK + ": " + ANSI_BG_BLACK + ANSI_RED + message.replace("\n", " ") + ANSI_RESET);
    }

    public static void warning(String message) {
        System.out.println(ANSI_BG_BLACK + LocalDate.now() + " " + LocalTime.now().toString().substring(0, 12) + ANSI_BLUE + "  INFO " + ANSI_PURPLE + PID + ANSI_RESET + ANSI_BG_BLACK + " --- [127.0.0.1 Log  ] " + ANSI_CYAN + "com.sulaymon.yahyo.alhamdulillah         " + ANSI_RESET + ANSI_BG_BLACK + ": " + ANSI_BG_GREEN + message.replace("\n", " ") + ANSI_RESET);
    }
}
