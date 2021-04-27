package fpu

import chisel3._
import chisel3.util._

class AluSign extends Module{
    val io = IO(new Bundle{
        val i_op = Input(UInt(3.W))
        val i_scr1 = Input(UInt(1.W))
        val i_scr2 = Input(UInt(1.W))
        val o_res = Output(UInt(1.W))
        val o_N = Output(Bool()) 
    })

    
}