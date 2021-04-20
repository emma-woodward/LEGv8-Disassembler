package com.company;

public class InstructionSet {

    Instruction[] instructionSet = {
            new Instruction("ADD", "0b10001011000"),
            new Instruction("ADDI", "0b1001000100"),
            new Instruction("ADDIS", "0b1011000100"),
            new Instruction("ADDS", "0b10101011000"),
            new Instruction("AND", "0b10001010000"),
            new Instruction("ANDI", "0b1001001000"),
            new Instruction("ANDIS", "0b1111001000"),
            new Instruction("ANDS", "0b1110101000"),
            new Instruction("B", "0b000101"),
            new Instruction("BR", "0b100101"),
            new Instruction("CBNZ", "0b10110101"),
            new Instruction("CBZ", "0b10110100"),
            new Instruction("DUMP", "0b11111111110"),
            new Instruction("EOR", "0b11001010000"),
            new Instruction("EORI", "0b1101001000"),
            new Instruction("FADDD", "0b00011110011"),
            new Instruction("FADDS", "0b00011110001"),
            new Instruction("FCMPD", "0b00011110011"),
            new Instruction("FCMPS", "0b00011110001"),
            new Instruction("FDIVD", "0b00011110011"),
            new Instruction("FDIVS", "0b00011110001"),
            new Instruction("FMULD", "0b00011110011"),
            new Instruction("FMULS", "0b00011110001"),
            new Instruction("FSUBD", "0b00011110011"),
            new Instruction("FSUBS", "0b00011110001"),
            new Instruction("HALT", "0b11111111111"),
            new Instruction("LDUR", "0b11111000010"),
            new Instruction("LDURB", "0b00111000010"),
            new Instruction("LDURD", "0b11111100010"),
            new Instruction("LDURH", "0b01111000010"),
            new Instruction("LDURS", "0b10111100010"),
            new Instruction("LDURSW", "0b10111000100"),
            new Instruction("LSL", "0b11010011011"),
            new Instruction("LSR", "0b11010011010"),
            new Instruction("MUL", "0b10011011000"),
            new Instruction("ORR", "0b10101010000"),
            new Instruction("ORRI", "0b1011001000"),
            new Instruction("PRNL", "0b11111111100"),
            new Instruction("PRNT", "0b11111111101"),
            new Instruction("SDIV", "0b10011010110"),
            new Instruction("SMULH", "0b10011011010"),
            new Instruction("STUR", "0b11111000000"),
            new Instruction("STURB", "0b00111000000"),
            new Instruction("STURD", "0b11111100000"),
            new Instruction("STURH", "0b01111000000"),
            new Instruction("STURS", "0b10111100000"),
            new Instruction("STURSW", "0b10111000000"),
            new Instruction("SUB", "0b11001011000"),
            new Instruction("SUBI", "0b1101000100"),
            new Instruction("SUBIS", "0b1111000100"),
            new Instruction("SUBS", "0b11101011000"),
            new Instruction("UDIV", "0b10011010110"),
            new Instruction("UMULH", "0b10011011110")
    };

}
