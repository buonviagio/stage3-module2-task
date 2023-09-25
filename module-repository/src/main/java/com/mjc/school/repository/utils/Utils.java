package com.mjc.school.repository.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.Random;

public class Utils {
    private static Utils createData;
    private static final Random random = new Random();

    public static Utils getInstance() {
        if (createData == null) {
            createData = new Utils();
        }
        return createData;
    }

    public String getReadedData(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader fr = new FileReader(fileName);
             BufferedReader br = new BufferedReader(fr)) {
            int numOfLine = random.nextInt(20);

            for (int i = 0; i < 20; i++) {
                br.readLine();
                if (numOfLine == i) {
                    stringBuilder.append(br.readLine());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }

    public LocalDateTime getDateTime() {
        byte days = 28;
        LocalDate localDate = LocalDate.now().minus(Period.ofDays(random.nextInt(days)));
        LocalTime localTime = LocalTime.of(random.nextInt(24), random.nextInt(60), random.nextInt(24));
        return LocalDateTime.of(localDate, localTime);
    }
}
