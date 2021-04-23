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


    public String constructString(String[] args) {
        String returnStr = null;

        switch (instructionType) {
            case "R":
                returnStr = instructionStr + " " + args[0] + ", " + args[1] + ", " + args[2];
                break;
            case "I":
                returnStr = instructionStr + " " + args[0] + ", " + args[1] + ", " + args[2];
                break;
            case "D":
                returnStr = instructionStr + " " + args[0] + ",[" + args[1] + ", " + args[2] + "]";
                break;
            case "B":
                returnStr = instructionStr + "LABEL";
                break;
            case "CB":
                returnStr = instructionStr + "LABEL";
                break;
            case "IW":
                returnStr = instructionStr + "YEAHHHH BOIIIIIIIIIIIIIIIIII";
                break;
            case "Z":
                returnStr = instructionStr;
                break;
            default:
                break;
        }

        return returnStr;
    }

}
