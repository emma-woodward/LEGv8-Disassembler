package com.company;

public class Instruction {

    private String instructionName;
    private int opcode;

    public Instruction(String instructionName, int opcode) {
        this.instructionName = instructionName;
        this.opcode = opcode;
    }

    public String getInstructionName(){
        return instructionName;
    }

    public boolean opcodeMatch(int op){
        return true;
    }
}
