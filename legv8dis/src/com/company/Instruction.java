package com.company;

public class Instruction {

    private String instructionStr;
    private String opcode;
    private String instructionType;

    public Instruction(String instructionStr, String opcode, String instructionType) {
        this.instructionStr = instructionStr;
        this.opcode = opcode;
        this.instructionType = instructionType;
    }

    public String getInstructionType(){ return instructionType; }

    public boolean doesOpcodeMatch(String op) {
        return op.compareTo(opcode) == 0;
    }

    public String getOpcode(){
        return opcode;
    }

    public String getInstructionName(){
        return instructionStr;
    }

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

    /*Special Registers:
    XZR: Register X31
    LR: Register X30
    FP: Register X29
    SP: Register X28
    * */
    private String getRegisterName(int register){
        return switch (register) {
            case 31 -> "XZR";
            case 30 -> "LR";
            case 29 -> "FP";
            case 28 -> "SP";
            default -> "X" + register;
        };
    }

    private String constructRString(String[] args){
        //Opcode, Rm, Shamt, Rn, Rd
        //Rd = Rn + Rm
        String Rn = getRegisterName(Integer.parseInt(args[3], 2));
        String Rm = getRegisterName(Integer.parseInt(args[1], 2));
        String Rd = getRegisterName(Integer.parseInt(args[4], 2));
        int Shamt = Integer.parseInt(args[2], 2);

        //These are the only instructions that use the shift amount
        if(instructionStr.equals("LSL") || instructionStr.equals("LSR")){
            return (instructionStr + " " + Rd + ", " + Rn + ", #" + Shamt);
        }

        return (instructionStr + " " + Rd + ", " + Rn + ", " + Rm);
    }

    private String constructIString(String[] args){
        int immediate = Integer.parseInt(args[1], 2);
        String Rn = getRegisterName(Integer.parseInt(args[2], 2));
        String Rd = getRegisterName(Integer.parseInt(args[3], 2));

        return (instructionStr + " " + Rd + ", " + Rn + ", #" + immediate);
    }

    private String constructDString(String[] args){
        //Rt = Rn + DT_add
        //Opcode, DT_add, op, Rn, Rt
        String Rn = getRegisterName(Integer.parseInt(args[3], 2));
        String Rt = getRegisterName(Integer.parseInt(args[4], 2));
        int immediate = Integer.parseInt(args[1], 2);

        return (instructionStr + " " + Rt + ", [" + Rn + ", #" + immediate + "]");
    }

    private String constructBString(String[] args){
        int address = Integer.parseInt(args[1], 2);

        return (instructionStr + " " + address);
    }

    private String constructCBString(String[] args){
        //Opcode, address, Rt
        String Rt = getRegisterName(Integer.parseInt(args[2], 2));
        int address = Integer.parseInt(args[1], 2);

        return (instructionStr + " " + Rt + ", " + address);
    }

    public String constructString(String[] args) {
        switch (instructionType) {
            case "R":
                return constructRString(args);
            case "I":
                return constructIString(args);
            case "D":
                return constructDString(args);
            case "B":
                return constructBString(args);
            case "CB":
                return constructCBString(args);
            default:
                return null;
        }
    }
}
