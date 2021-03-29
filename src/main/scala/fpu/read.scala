package fpu

import chisel3._
import chisel3.util._

class Read extends Module{
    val io = IO(new Bundle{
        val i_inst = Input(UInt(32.W))
        val i_en = Input(Bool())
        val o_op1 = Output(UInt(32.W))
        val o_op2 = Output(UInt(32.W))
    })
    val op = Wire(UInt(7.W))
    op := io.i_inst(6,0)
    
    
    val rs1 = Vec(5,io.i_inst(19,15))
    val rs2 = Vec(5, io.i_inst(24,0))
    val rd = Vec(5, io.i_inst(6,0))
}
