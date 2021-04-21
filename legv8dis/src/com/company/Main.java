package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

         Instruction[] instructionSet = {
                new Instruction("ADD", "0b10001011000", "R"),
                new Instruction("ADDI", "0b1001000100", "I"),
                new Instruction("ADDIS", "0b1011000100", "I"),
                new Instruction("ADDS", "0b10101011000", "R"),
                new Instruction("AND", "0b10001010000", "R"),
                new Instruction("ANDI", "0b1001001000", "I"),
                new Instruction("ANDIS", "0b1111001000", "I"),
                new Instruction("ANDS", "0b1110101000", "R"),
                new Instruction("B", "0b000101", "B"),
                new Instruction("BR", "0b100101", "R"),
                new Instruction("CBNZ", "0b10110101", "CB"),
                new Instruction("CBZ", "0b10110100", "CB"),
                new Instruction("DUMP", "0b11111111110", "Z"),
                new Instruction("EOR", "0b11001010000", "R"),
                new Instruction("EORI", "0b1101001000", "I"),
                new Instruction("FADDD", "0b00011110011", "R"),
                new Instruction("FADDS", "0b00011110001", "R"),
                new Instruction("FCMPD", "0b00011110011", "R"),
                new Instruction("FCMPS", "0b00011110001", "R"),
                new Instruction("FDIVD", "0b00011110011", "R"),
                new Instruction("FDIVS", "0b00011110001", "R"),
                new Instruction("FMULD", "0b00011110011", "R"),
                new Instruction("FMULS", "0b00011110001", "R"),
                new Instruction("FSUBD", "0b00011110011", "R"),
                new Instruction("FSUBS", "0b00011110001", "R"),
                new Instruction("HALT", "0b11111111111", "Z"),
                new Instruction("LDUR", "0b11111000010", "D"),
                new Instruction("LDURB", "0b00111000010", "D"),
                new Instruction("LDURD", "0b11111100010", "D"),
                new Instruction("LDURH", "0b01111000010", "D"),
                new Instruction("LDURS", "0b10111100010", "D"),
                new Instruction("LDURSW", "0b10111000100", "D"),
                new Instruction("LSL", "0b11010011011", "R"),
                new Instruction("LSR", "0b11010011010", "R"),
                new Instruction("MUL", "0b10011011000", "R"),
                new Instruction("ORR", "0b10101010000", "R"),
                new Instruction("ORRI", "0b1011001000", "I"),
                new Instruction("PRNL", "0b11111111100", "Z"),
                new Instruction("PRNT", "0b11111111101", "Z"),
                new Instruction("SDIV", "0b10011010110", "R"),
                new Instruction("SMULH", "0b10011011010", "R"),
                new Instruction("STUR", "0b11111000000", "D"),
                new Instruction("STURB", "0b00111000000", "D"),
                new Instruction("STURD", "0b11111100000", "D"),
                new Instruction("STURH", "0b01111000000", "D"),
                new Instruction("STURS", "0b10111100000", "D"),
                new Instruction("STURSW", "0b10111000000", "D"),
                new Instruction("SUB", "0b11001011000", "R"),
                new Instruction("SUBI", "0b1101000100", "I"),
                new Instruction("SUBIS", "0b1111000100", "I"),
                new Instruction("SUBS", "0b11101011000", "R"),
                new Instruction("UDIV", "0b10011010110", "R"),
                new Instruction("UMULH", "0b10011011110", "R")
        };

        String DEV_FILEPATH = "C:/Users/Woodward/Documents/LEGv8-Disassembler/test.legv8asm.machine";
        boolean developerMode = true;

        if(args.length == 0 && !developerMode){
            System.out.println("Hello I am a disassembler, please give me a file to disassemble!");
        }

        try{
            //TODO: Change this to args[0] when handing in the project
            File f = new File(DEV_FILEPATH);
            FileInputStream fs = new FileInputStream(f);
            ArrayList<String> instructions = new ArrayList<String>();

            while(fs.available() > 0){
                byte[] bArr = fs.readNBytes(4);
                int theInstructionInt = Byte.toUnsignedInt(bArr[0]);
                theInstructionInt += Byte.toUnsignedInt(bArr[1]);
                theInstructionInt = theInstructionInt << 8;
                theInstructionInt += Byte.toUnsignedInt(bArr[2]);
                theInstructionInt = theInstructionInt << 8;
                theInstructionInt += Byte.toUnsignedInt(bArr[3]);
                instructions.add(Integer.toBinaryString(theInstructionInt));
            }

            fs.close();

            for(int i = 0; i < instructions.size(); i++){
                String str = instructions.get(i);
                String opcode = str.substring(0, 12);

                for(Instruction s : instructionSet){
                    if(s.opcodeMatch(opcode)){

                    }
                }
            }


        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
