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

    public boolean opcodeMatch(String op) {
        return op.compareTo(opcode) == 0;
    }

    public String[] breakInstruction(String i) {
        String[] brokenDown = new String[5];

        switch (instructionType) {
            case "R": //R: Rm 5 bits, shamt 6 bits, Rn 5 bits, Rd 5 bits
                brokenDown[0] = i.substring(0, 11); //Opcode 11 bits
                brokenDown[1] = i.substring(11, 17); //Rm 5 bits
                brokenDown[2] = i.substring(17, 24); //Shamt 6 bits
                brokenDown[3] = i.substring(24, 30); //Rn 5 bits
                brokenDown[4] = i.substring(30, 35); //Rd 5 bits
                break;
            case "I": //I: immediate 11 bits, Rn 5 bits, Rd 5 bits
                brokenDown[0] = i.substring(0, 11); //Opcode 11 bits
                brokenDown[1] = i.substring(11, 17); //Immediate 11 bits
                brokenDown[2] = i.substring(17, 24); //Rn 5 bits
                brokenDown[3] = i.substring(24, 30); //Rd 5 bits
                brokenDown[4] = i.substring(30, 35); //Null
                break;
            case "D": //D: DT_address 9 bits, op 2 bits, Rn 5 bits, Rt 5 bits
                brokenDown[0] = i.substring(0, 11); //Opcode 11 bits
                brokenDown[1] = i.substring(11, 17); //DT_address 9 bits
                brokenDown[2] = i.substring(17, 24); //op 2 bits
                brokenDown[3] = i.substring(24, 30); //Rn 5 bits
                brokenDown[4] = i.substring(30, 35); //Rt 5 bits
                break;
            case "B": //B: BR_address 26 bits
                brokenDown[0] = i.substring(0, 11); //Opcode 11 bits
                brokenDown[1] = i.substring(11, 17); //BR_address 26 bits
                brokenDown[2] = i.substring(17, 24); //Null
                brokenDown[3] = i.substring(24, 30); //Null
                brokenDown[4] = i.substring(30, 35); //Null
                break;
            case "CB": //COND_BR_address 19 bits, Rt 5 bits
                brokenDown[0] = i.substring(0, 11); //Opcode 11 bits
                brokenDown[1] = i.substring(11, 17); //COND_BR_address 19 bits
                brokenDown[2] = i.substring(17, 24); //Rt 5 bits
                brokenDown[3] = i.substring(24, 30); //Null
                brokenDown[4] = i.substring(30, 35); //Null
                break;
            case "IW": //IW: MOV_immediate 16 bits, Rd 5 bits
                brokenDown[0] = i.substring(0, 11); //Opcode 11 bits
                brokenDown[1] = i.substring(11, 17); //MOV_immediate 16 bits
                brokenDown[2] = i.substring(17, 24); //Rd 5 bits
                brokenDown[3] = i.substring(24, 30); //Null
                brokenDown[4] = i.substring(30, 35); //Null
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
