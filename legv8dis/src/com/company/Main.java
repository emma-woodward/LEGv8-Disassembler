package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

         Instruction[] instructionSet = {
                new Instruction("ADD", "10001011000", "R"),
                new Instruction("ADDI", "1001000100", "I"),
                new Instruction("ADDIS", "1011000100", "I"),
                new Instruction("ADDS", "10101011000", "R"),
                new Instruction("AND", "10001010000", "R"),
                new Instruction("ANDI", "1001001000", "I"),
                new Instruction("ANDIS", "1111001000", "I"),
                new Instruction("ANDS", "1110101000", "R"),
                new Instruction("B", "000101", "B"),
                new Instruction("BR", "100101", "R"), //TODO: BR instruction here doesn't 100% look like the one on the sheet
                new Instruction("CBNZ", "10110101", "CB"),
                new Instruction("CBZ", "10110100", "CB"), //TODO:
                new Instruction("DUMP", "11111111110", "R"),
                new Instruction("EOR", "11001010000", "R"),
                new Instruction("EORI", "1101001000", "I"),
                new Instruction("FADDD", "00011110011", "R"),
                new Instruction("FADDS", "00011110001", "R"),
                new Instruction("FCMPD", "00011110011", "R"),
                new Instruction("FCMPS", "00011110001", "R"),
                new Instruction("FDIVD", "00011110011", "R"),
                new Instruction("FDIVS", "00011110001", "R"),
                new Instruction("FMULD", "00011110011", "R"),
                new Instruction("FMULS", "00011110001", "R"),
                new Instruction("FSUBD", "00011110011", "R"),
                new Instruction("FSUBS", "00011110001", "R"),
                new Instruction("HALT", "11111111111", "R"),
                new Instruction("LDUR", "11111000010", "D"),
                new Instruction("LDURB", "00111000010", "D"),
                new Instruction("LDURD", "11111100010", "D"),
                new Instruction("LDURH", "01111000010", "D"),
                new Instruction("LDURS", "10111100010", "D"),
                new Instruction("LDURSW", "10111000100", "D"),
                new Instruction("LSL", "11010011011", "R"),
                new Instruction("LSR", "11010011010", "R"),
                new Instruction("MUL", "10011011000", "R"),
                new Instruction("ORR", "10101010000", "R"),
                new Instruction("ORRI", "1011001000", "I"),
                new Instruction("PRNL", "11111111100", "R"),
                new Instruction("PRNT", "11111111101", "R"),
                new Instruction("SDIV", "10011010110", "R"),
                new Instruction("SMULH", "10011011010", "R"),
                new Instruction("STUR", "11111000000", "D"),
                new Instruction("STURB", "00111000000", "D"),
                new Instruction("STURD", "11111100000", "D"),
                new Instruction("STURH", "01111000000", "D"),
                new Instruction("STURS", "10111100000", "D"),
                new Instruction("STURSW", "10111000000", "D"),
                new Instruction("SUB", "11001011000", "R"),
                new Instruction("SUBI", "1101000100", "I"),
                new Instruction("SUBIS", "1111000100", "I"),
                new Instruction("SUBS", "11101011000", "R"),
                new Instruction("UDIV", "10011010110", "R"),
                new Instruction("UMULH", "10011011110", "R")
        };

        String DEV_FILEPATH = "C:/Users/Woodward/Documents/LEGv8-Disassembler/test.legv8asm.machine";
        boolean developerMode = true;

        if(args.length == 0 && !developerMode){
            System.out.println("Hello I am a disassembler, please give me a file to disassemble :)");
        }

        try{
            //TODO: Change this to args[0] when handing in the project
            FileInputStream fs = new FileInputStream(new File(DEV_FILEPATH));
            ArrayList<String> instructions = new ArrayList<String>();

            //Reading in bytes and converting them into a binary string
            //that will be used to interpret each instruction then
            //stores those binary strings in a String[]
            while(fs.available() > 0){
                byte[] bArr = fs.readNBytes(4);
                int theInstructionInt = Byte.toUnsignedInt(bArr[0]);

                theInstructionInt = theInstructionInt << 8;
                theInstructionInt += Byte.toUnsignedInt(bArr[1]);

                theInstructionInt = theInstructionInt << 8;
                theInstructionInt += Byte.toUnsignedInt(bArr[2]);

                theInstructionInt = theInstructionInt << 8;
                theInstructionInt += Byte.toUnsignedInt(bArr[3]);

                instructions.add(Integer.toBinaryString(theInstructionInt));
            }

            fs.close();

            for (String currentInstruction : instructions) {
                //Figure out which instruction the opcode belongs to

                System.out.println(currentInstruction);

                //Make sure the instruction is 32 bits wide
                //This is to counter act Integer.toBinaryString() by adding back the leading zeros that it takes out
                if(currentInstruction.length() < 32){
                    String temp = "";

                    for(int i = 0; i < (32-currentInstruction.length()); i++){
                        temp += "0";
                    }

                    currentInstruction = temp + currentInstruction;
                }

                System.out.println(currentInstruction + "\n");

                //Opcodes can be 6, 8, 9, 10, 11 bits wide
                String o1 = currentInstruction.substring(0, 11);
                String o2 = currentInstruction.substring(0, 10);
                String o3 = currentInstruction.substring(0, 9);
                String o4 = currentInstruction.substring(0, 8);
                String o5 = currentInstruction.substring(0, 6);

                int matchedIndex = -1;

                //Check for all combinations of possible bit widths
                for(int i = 0; i < instructionSet.length; i++){
                    if(instructionSet[i].doesOpcodeMatch(o1) || instructionSet[i].doesOpcodeMatch(o2) || instructionSet[i].doesOpcodeMatch(o3) ||
                       instructionSet[i].doesOpcodeMatch(o4) || instructionSet[i].doesOpcodeMatch(o5)){
                        matchedIndex = i;
                    }
                }

                //Something went wrong
                if(matchedIndex == -1){
                    throw new NoSuchMethodException();
                }

                //At this point we know which instruction is being executed so we can call the method
                //that breaks down the instruction in the Instruction class
                String[] brokenDownInstruction = instructionSet[matchedIndex].breakInstruction(currentInstruction);

                System.out.println(instructionSet[matchedIndex].getInstructionName());

                for(String s : brokenDownInstruction){
                    if(s != null){
                        System.out.println(s);
                    }
                }

                System.out.println("");

            }
        }
        catch(NoSuchMethodException nsme){
            System.out.println("One or more instructions are invalid...");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
