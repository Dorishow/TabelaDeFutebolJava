package com.table.utils.filesGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FilesWriter {

    public static boolean Write(String path, String text){
        boolean wroteFile = false;
        try {
            FileWriter file = new FileWriter(path);
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.println(text);
            printWriter.close();
            wroteFile = true;
            System.out.printf("Arquivo criado em %s%n", path);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return wroteFile;
    }
}
