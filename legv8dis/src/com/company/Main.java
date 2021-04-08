package com.company;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String DEV_FILEPATH = "C:/Users/Woodward/Documents/LEGv8-Disassembler/test.legv8asm.machine";

        if(args.length == 0){
            System.out.println("Hello I am a disassembler, please give me a file to disassemble!");
        }

        try{
            //TODO: Change this to args[0] when handing in the project
            File f = new File(DEV_FILEPATH);
            Scanner s = new Scanner(f);

            System.out.println(s.nextLine());

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
