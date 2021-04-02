package fpu

import chisel3._
import chisel3.util._

class Read extends Module{
    val io = IO(new Bundle{
        val src1 = Input(UInt(32.W))
        val src2 = Input(UInt(32.W))
        val opcode = Input (UInt(3.W))
        val rd = Input (UInt(5.W))

        val i_opE = Output(UInt(3.W))
        val i_opM = Output(UInt(3.W))
        val i_S1 = Output(UInt(1.W))
        val i_S2 = Output(UInt(1.W))
        val i_E1 = Output(SInt(8.W))
        val i_E2 = Output(SInt(8.W))
        val i_M1 = Output(UInt(23.W))
        val i_M2 = Output(UInt(23.W))))
    })

 
}
