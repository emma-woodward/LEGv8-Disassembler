package com.company;

public class Instruction {

    private String instructionStr;
    private String opcode;

    public Instruction(String instructionStr, String opcode) {
        this.instructionStr = instructionStr;
        this.opcode = opcode;
    }

    public String getInstructionString(){
        return instructionStr;
    }

    public boolean opcodeMatch(int op){
        return true;
    }
}
