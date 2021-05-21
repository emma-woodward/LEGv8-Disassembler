/**
 * @author Vincent Woodward (https://github.com/vincent-woodward)
 */

package com.company;

/**
 * Instruction class stores all methods needed regarding instructions.
 */
public class Instruction {

    /**
     * The instruction string (LDUR, STUR, ADDI, etc)
     */
    private String instructionStr;
    /**
     * Instruction opcode as a String
     */
    private String opcode;
    /**
     * Instruction type (R, I, D, B, CB, IW)
     */
    private String instructionType;

    /**
     * Instruction class constructor
     *
     * @param instructionStr Instruction string (LDUR, STUR, ADDI, etc)
     * @param opcode Opcode as a String
     * @param instructionType The type of the instruction (R, I, D, B, CB, IW)
     *                        @author Vincent Woodward (https://github.com/vincent-woodward)
     */
    public Instruction(String instructionStr, String opcode, String instructionType) {
        this.instructionStr = instructionStr;
        this.opcode = opcode;
        this.instructionType = instructionType;
    }

    /**
     * Returns the instruction type as a String
     *
     * @return String instruction type
     * @author Vincent Woodward (https://github.com/vincent-woodward)
     */
    public String getInstructionType(){ return instructionType; }

    /**
     * Checks whether or not the passed in opcode matches with this particular Instruction
     *
     * @param op String to compare with this class
     * @return True if the opcodes match, false otherwise
     * @author Vincent Woodward (https://github.com/vincent-woodward)
     */
    public boolean doesOpcodeMatch(String op) {
        return op.compareTo(opcode) == 0;
    }

    /**
     * Returns the opcode for this Instruction
     *
     * @return String opcode
     * @author Vincent Woodward (https://github.com/vincent-woodward)
     */
    public String getOpcode(){
        return opcode;
    }

    /**
     * Gets the instruction name or str (LDUR, STUR, ADDI, etc)
     *
     * @return String instruction name
     * @author Vincent Woodward (https://github.com/vincent-woodward)
     */
    public String getInstructionName(){
        return instructionStr;
    }

    /**
     * Breaks the instruction down into workable parts and returns the mas a String[]
     *
     * @param i Given instruction
     * @return String[] with each part of the instruction. Indexed from 0 to 4.
     * @author Vincent Woodward (https://github.com/vincent-woodward)
     */
    public String[] breakInstruction(String i) {
        String[] brokenDown = new String[5];

        switch (instructionType) {
            case "R": //R: Opcode 11 bits, Rm 5 bits, shamt 6 bits, Rn 5 bits, Rd 5 bits
                brokenDown[0] = i.substring(0, 11); //Opcode 11 bits
                brokenDown[1] = i.substring(11, 16); //Rm 5 bits
                brokenDown[2] = i.substring(16, 22); //Shamt 6 bits
                brokenDown[3] = i.substring(22, 27); //Rn 5 bits 22,23,24,25,26,27,28,29,30,31
                brokenDown[4] = i.substring(27); //Rd 5 bits
                break;
            case "I": //I: Opcode 10 bits, immediate 12 bits, Rn 5 bits, Rd 5 bits
                brokenDown[0] = i.substring(0, 10); //Opcode 10 bits
                brokenDown[1] = i.substring(10, 22); //Immediate 11 bits
                brokenDown[2] = i.substring(22, 27); //Rn 5 bits
                brokenDown[3] = i.substring(27); //Rd 5 bits
                break;
            case "D": //D: Opcode 11 bits, DT_address 9 bits, op 2 bits, Rn 5 bits, Rt 5 bits
                brokenDown[0] = i.substring(0, 11); //Opcode 11 bits
                brokenDown[1] = i.substring(11, 20); //DT_address 9 bits
                brokenDown[2] = i.substring(20, 22); //op 2 bits
                brokenDown[3] = i.substring(22, 27); //Rn 5 bits
                brokenDown[4] = i.substring(27); //Rt 5 bits
                break;
            case "B": //B: Opcode 6 bits, BR_address 26 bits
                brokenDown[0] = i.substring(0, 6); //Opcode 6 bits
                brokenDown[1] = i.substring(6); //BR_address 26 bits
                break;
            case "CB": //CB: Opcode 8 bits, COND_BR_address 19 bits, Rt 5 bits
                brokenDown[0] = i.substring(0, 8); //Opcode 8 bits
                brokenDown[1] = i.substring(8, 27); //COND_BR_address 19 bits
                brokenDown[2] = i.substring(27); //Rt 5 bits
                break;
            case "IW": //IW: Opcode 11 bits, IW: MOV_immediate 16 bits, Rd 5 bits
                //IW not supported
                break;
            default:
                break;
        }

        return brokenDown;
    }

    /**
     * Given a register number, such as X31 it gives the appropriate register String./
     *
     * Special register names: XZR (31), LR (30), FP (29), SP (28).
     *
     * @param register integer representing which register
     * @return String that is the correct register String. If 'special' it will give the name, XREGISTER otherwise.
     * @author Vincent Woodward (https://github.com/vincent-woodward)
     */
    private String getRegisterName(int register){
        return switch (register) {
            case 31 -> "XZR";
            case 30 -> "LR";
            case 29 -> "FP";
            case 28 -> "SP";
            default -> "X" + register;
        };
    }

    /**
     * Constructs B type instructions from a broken down instruction
     *
     * @param args String[] broken down instruction
     * @return String B type string
     * @author Vincent Woodward (https://github.com/vincent-woodward)
     */
    private String constructRString(String[] args){
        //Opcode, Rm, Shamt, Rn, Rd
        //Rd = Rn + Rm
        String Rn = getRegisterName(Integer.parseInt(args[3], 2));
        String Rm = getRegisterName(Integer.parseInt(args[1], 2));
        String Rd = getRegisterName(Integer.parseInt(args[4], 2));
        int Shamt = Integer.parseInt(args[2], 2);

        if(instructionStr.equals("BR")){
            return (instructionStr + " " + Rn);
        }

        if(instructionStr.equals("PRNT")){
            return (instructionStr + " " + Rd);
        }

        if(instructionStr.equals("PRNL") || instructionStr.equals("DUMP") || instructionStr.equals("HALT")){
            return instructionStr;
        }

        //These are the only instructions that use the shift amount
        if(instructionStr.equals("LSL") || instructionStr.equals("LSR")){
            return (instructionStr + " " + Rd + ", " + Rn + ", #" + Shamt);
        }

        return (instructionStr + " " + Rd + ", " + Rn + ", " + Rm);
    }

    /**
     * Constructs an I type Instruction as a String
     *
     * @param args String[] broken down instruction
     * @return String I type instruction
     * @author Vincent Woodward (https://github.com/vincent-woodward)
     */
    private String constructIString(String[] args){
        int immediate = Integer.parseInt(args[1], 2);
        String Rn = getRegisterName(Integer.parseInt(args[2], 2));
        String Rd = getRegisterName(Integer.parseInt(args[3], 2));

        return (instructionStr + " " + Rd + ", " + Rn + ", #" + immediate);
    }

    /**
     * Constructs a D type instruction as a String
     *
     * @param args String[] broken down instruction
     * @return String D type instruction
     * @author Vincent Woodward (https://github.com/vincent-woodward)
     */
    private String constructDString(String[] args){
        //Rt = Rn + DT_add
        //Opcode, DT_add, op, Rn, Rt
        String Rn = getRegisterName(Integer.parseInt(args[3], 2));
        String Rt = getRegisterName(Integer.parseInt(args[4], 2));
        int immediate = Integer.parseInt(args[1], 2);

        return (instructionStr + " " + Rt + ", [" + Rn + ", #" + immediate + "]");
    }

    /**
     * Constructs a B type instruction as a String
     *
     * @param args String[] broken down instruction
     * @param isUnreasonbale if the displacement is unreasonable
     * @return String B type instruction
     * @author Vincent Woodward (https://github.com/vincent-woodward)
     */
    private String constructBString(String[] args, boolean isUnreasonbale){
        int address = Integer.parseInt(args[1], 2);

        if(isUnreasonbale){
            address--;
            String temp = Integer.toBinaryString(address);

            if(temp.length() > 23){
                //Shorten the string
                temp = temp.substring(temp.length() - 24);

            }

            //Invert it

            char[] arr = temp.toCharArray();
            temp = "";

            for(int i = 0; i < arr.length; i++) {
                if (arr[i] == '0') {
                    temp += "1";
                } else {
                    temp += "0";
                }
            }

            address = Integer.parseInt(temp, 2);
            address = -address;
        }

        return (instructionStr + " " + address);
    }

    /**
     * Returns the correct String for a given condition
     *
     * @param cond condition
     * @return String that is the correct B condition
     * @author Vincent Woodward (https://github.com/vincent-woodward)
     */
    private String getBCond(int cond){
        switch(cond){
            case 0:
                return "B.EQ";
            case 1:
                return "B.NE";
            case 2:
                return "B.HS";
            case 3:
                return "B.LO";
            case 4:
                return "B.MI";
            case 5:
                return "B.PL";
            case 6:
                return "B.VS";
            case 7:
                return "B.VC";
            case 8:
                return "B.HI";
            case 9:
                return "B.LS";
            case 10:
                return "B.GE";
            case 11:
                return "B.LT";
            case 12:
                return "B.GT";
            case 13:
                return "B.LE";
            default:
                return null;
        }
    }

    /**
     * Constructs a CB type instruction as a String
     *
     * @param args String[] broken down instruction
     * @param isUnreasonbale if the displacement is unreasonable
     * @return String CB type instruction
     * @author Vincent Woodward (https://github.com/vincent-woodward)
     */
    private String constructCBString(String[] args, boolean isUnreasonbale){
        //Opcode, address, Rt
        if(instructionStr.equals("B.")){
            String newInstructionName = getBCond(Integer.parseInt(args[2], 2));
            int address = Integer.parseInt(args[1], 2);

            if(isUnreasonbale){
                address--;
                String temp = Integer.toBinaryString(address);

                if(temp.length() > 23){
                    //Shorten the string
                    temp = temp.substring(temp.length() - 24);
                    address = Integer.parseInt(temp);
                }
            }

            return (newInstructionName + " " + address);
        }

        String Rt = getRegisterName(Integer.parseInt(args[2], 2));
        int address = Integer.parseInt(args[1], 2);

        return (instructionStr + " " + Rt + ", " + address);
    }

    /**
     * Helper method that identifies the correct method to use for the construction of a instruction
     *
     * @param args String[] broken down instruction
     * @param isUnreasonable if the displacement is unreasonable
     * @return String instruction
     * @author Vincent Woodward (https://github.com/vincent-woodward)
     */
    public String constructString(String[] args, boolean isUnreasonable) {
        switch (instructionType) {
            case "R":
                return constructRString(args);
            case "I":
                return constructIString(args);
            case "D":
                return constructDString(args);
            case "B":
                return constructBString(args, isUnreasonable);
            case "CB":
                return constructCBString(args, isUnreasonable);
            default:
                return null;
        }
    }
}
