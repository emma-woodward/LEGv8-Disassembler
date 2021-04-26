# LEGv8-Disassembler
As a part of my COM S 321 (Introduction to Computer Architecture and Machine-Level Programming) class at Iowa State University I had to create a disassembler. The disassembler takes a binary LEG V8 file (a subset of ARM V8) and converts it back to its assembly form without the original label names and without any comments made in the original assembly program.

This was created using Java and standard Java libraries for everything.

Running the disassembler:
java -jar legv8dis.jar FILE.legv8asm.machine

Running the program like the above will diassemble the file and output the assembly in the console. I have also implemented a -F option to output the assembly as a file which will be saved as output.legv8asm.
