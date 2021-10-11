# LEGv8-Disassembler
As a part of my COM S 321 (Introduction to Computer Architecture and Machine-Level Programming) class at Iowa State University I had to create a disassembler. The disassembler takes a binary LEGv8 file (a subset of ARMv8) and converts it back to its assembly form without the original label names and without any comments made in the original assembly program.

This was created using Java 15.0.1 and the standard Java libraries.

Running the disassembler:
- Create a .jar
- Enter this into a terminal: java -jar legv8dis.jar FILE.legv8asm.machine

Running the program like the above will diassemble the file and output the assembly in the terminal. 

Implemented ARM Instructions:
- [x] ADDI
- [x] ADDIS
- [x] ADDS
- [x] AND
- [x] ANDI
- [x] ANDIS
- [x] ANDS
- [x] B
- [x] BR
- [x] CBNZ
- [x] CBZ
- [x] EOR
- [x] EORI
- [x] FADDD
- [x] FADDDS
- [x] FCMPD
- [x] FCMPS
- [x] FDIVD
- [x] FDIVS
- [x] FMULD
- [x] FMULS
- [x] FSUBD
- [x] FSUBS
- [x] LDUR
- [x] LDURB
- [x] LDURD
- [x] LDURH
- [x] LDURS
- [x] LDURSW
- [x] LSL
- [x] LSR
- [x] MUL
- [x] ORR
- [x] ORRI
- [x] SDIV
- [x] SMULH
- [x] STUR
- [x] STURB
- [x] STURD
- [x] STURH
- [x] STURS
- [x] STURSW
- [x] SUB
- [x] SUBI
- [x] SUBIS
- [x] SUBS
- [x] UDIV
- [x] UMULH
- [x] B
- [x] BL
- [x] B.COND

Non-ARM Instructions Used For Debugging While Writing Assembly:
- [x] DUMP
- [x] HALT
- [x] PRNL
- [x] PRNT
