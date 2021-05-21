/**
 * @author Vincent Woodward (https://github.com/vincent-woodward)
 */

package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    /**
     * Entry point for the disassembler
     *
     * @param args the input for the program
     *             The only input for the program is the file of a .machine binary file
     * @author Vincent Woodward (https://github.com/vincent-woodward)
     */
    public static void main(String[] args) {

        /**
         * This is the LEGv8 instruction set as prescribed in the Computer Organization And Design ARM Edition textbook
         * with a few other instructions used for debugging when writing assembly:
         *  - PRNT - prints a register
         *  - PRNL - prints a new line
         *  - DUMP - dumps all the registers and halts the program
         *  - HALT - halts the program
         */
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
                new Instruction("BR", "11010110000", "R"),
                new Instruction("CBNZ", "10110101", "CB"),
                new Instruction("CBZ", "10110100", "CB"),
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
                new Instruction("UMULH", "10011011110", "R"),
                new Instruction("B.", "01010100", "CB"),
                new Instruction("BL", "100101", "B")
        };

        if (args.length == 0) {
            //When there isn't a file given, this just allows the user to know that in fact the program did run.
            System.out.println("Hello I am a disassembler, please give me a file to disassemble :)");
        } else {
            try {
                FileInputStream fs = new FileInputStream(new File(args[0]));
                ArrayList<String> instructions = new ArrayList<String>();
                ArrayList<String> ARMInstructions = new ArrayList<String>();
                ArrayList<Integer> branchingInstructions = new ArrayList<Integer>();

                //Reading in bytes and converting them into a binary string
                //that will be used to interpret each instruction then
                //stores those binary strings in a String[]
                while (fs.available() > 0) {
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

                for (int p = 0; p < instructions.size(); p++) {
                    String currentInstruction = instructions.get(p);

                    //Figure out which instruction the opcode belongs to

                    //Make sure the instruction is 32 bits wide
                    //This is to counter act Integer.toBinaryString() by adding back the leading zeros that it takes out
                    while (currentInstruction.length() < 32) {
                        currentInstruction = "0" + currentInstruction;
                    }

                    //Opcodes can be 6, 8, 9, 10, 11 bits wide
                    String o1 = currentInstruction.substring(0, 11);
                    String o2 = currentInstruction.substring(0, 10);
                    String o3 = currentInstruction.substring(0, 9);
                    String o4 = currentInstruction.substring(0, 8);
                    String o5 = currentInstruction.substring(0, 6);

                    int matchedIndex = -1;

                    //Check for all combinations of possible bit widths
                    for (int i = 0; i < instructionSet.length; i++) {
                        if (instructionSet[i].doesOpcodeMatch(o1) || instructionSet[i].doesOpcodeMatch(o2) || instructionSet[i].doesOpcodeMatch(o3) ||
                                instructionSet[i].doesOpcodeMatch(o4) || instructionSet[i].doesOpcodeMatch(o5)) {
                            matchedIndex = i;
                        }
                    }

                    //Something went wrong
                    if (matchedIndex == -1) {
                        System.out.println(currentInstruction);
                        throw new NoSuchMethodException();
                    }

                    //At this point we know which instruction is being executed so we can call the method
                    //that breaks down the instruction in the Instruction class
                    String[] brokenDownInstruction = instructionSet[matchedIndex].breakInstruction(currentInstruction);

                    String curInstructionType = instructionSet[matchedIndex].getInstructionType();

                    if (curInstructionType == "B" || curInstructionType == "CB") {
                        branchingInstructions.add(p);
                    }

                    ARMInstructions.add(instructionSet[matchedIndex].constructString(brokenDownInstruction, false));
                }

                //At this point we know all of the instructions, we just need to do the labels properly.
                //Go through each instruction in the list of instructions that use labels
                ArrayList<Label> labels = new ArrayList<Label>();

                for (int callerIndex : branchingInstructions) {
                    String currentInstruction = ARMInstructions.get(callerIndex);
                    Scanner scan = new Scanner(currentInstruction);
                    ArrayList<String> temp = new ArrayList<String>();

                    while (scan.hasNext()) {
                        temp.add(scan.next());
                    }

                    int offset = Integer.parseInt(temp.get(temp.size() - 1));
                    int labelIndex = callerIndex + offset;

                    //Check if label index is reasonable, if not take the original binary: subtract 1, and invert
                    if (labelIndex > instructions.size()) {
                        Instruction theInstruction = null;

                        for (Instruction m : instructionSet) {
                            if (m.getInstructionName().equals(temp.get(0))) {
                                theInstruction = m;
                                break;
                            }
                        }

                        if (theInstruction == null) {
                            throw new Exception();
                        }

                        String[] currentInstructionArgs = theInstruction.breakInstruction(instructions.get(callerIndex));
                        String newInstruction = theInstruction.constructString(currentInstructionArgs, true);
                        ARMInstructions.set(callerIndex, newInstruction);
                        currentInstruction = newInstruction;

                        scan = new Scanner(currentInstruction);
                        String tempStr = "";

                        while (scan.hasNext()) {
                            tempStr = scan.next();
                        }

                        offset = Integer.parseInt(tempStr);

                        //Have offset now, find index of where label should be and check if there's a label there already
                        labelIndex = callerIndex + offset;

                    }

                    String labelName = "label_" + labels.size();
                    labels.add(new Label(labelName, labelIndex));

                    String addThis = (temp.get(0) + " ");

                    for (int i = 1; i < temp.size() - 1; i++) {
                        addThis += temp.get(i) + " ";
                    }

                    addThis += (labelName);

                    ARMInstructions.set(callerIndex, addThis);
                }

                //Sort labels so that it's from largest to smallest so that it doesn't disrupt indexing of the labels
                for (int i = 0; i < labels.size() - 1; i++) {
                    int greatestLabelIndex = i;

                    for (int j = i + 1; j < labels.size(); j++) {
                        Label l2 = labels.get(j);

                        if (labels.get(greatestLabelIndex).getIndex() < l2.getIndex()) {
                            greatestLabelIndex = j;
                        }
                    }

                    //Swap
                    if (greatestLabelIndex != -1) {
                        Label l1 = labels.get(i);
                        labels.set(i, labels.get(greatestLabelIndex));
                        labels.set(greatestLabelIndex, l1);
                    }
                }

                //Insert labels
                for (Label l : labels) {
                    ARMInstructions.add(l.getIndex(), l.getName() + ":");
                }

                for (String str : ARMInstructions) {
                    System.out.println(str);
                }
            } catch (NoSuchMethodException nsme) {
                System.out.println("One or more instructions are invalid...");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
