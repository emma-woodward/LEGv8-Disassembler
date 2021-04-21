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

    public String getInstructionString(){
        return instructionStr;
    }

    public boolean opcodeMatch(String op){
        return op.compareTo(opcode) == 0;
    }
    
    public String toString(int immediate, String Rm, String Rn, String Rd, String Rt){
        String returnStr = null;

        switch(instructionType){
            case "R":
                returnStr = instructionStr + " " + Rd + ", " + Rn + ", " + Rm;
                break;
            case "I":
                returnStr = instructionStr + " " + Rd + ", " + Rn + ", " + Rm;
                break;
            case "D":
                returnStr = instructionStr + " " + Rt + ",[" + Rn + ", " + immediate + "]";
                break;
            case "B":
                returnStr = instructionStr + "LABEL";
                break;
            case "CB":
                returnStr = instructionStr + "LABEL";
                break;
            case "IW":
                returnStr = instructionStr + "MOVZ";
                break;
            case "Z":
                returnStr = "YEAHHH BOIII";
                break;
            default:
                break;
        }

        return returnStr;
    }

}
