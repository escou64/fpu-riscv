package fpu

import chisel3._
import chisel3.util._

class Calculation extends Module{
    val io = IO( new Bundle{
        val i_opM = Input(UInt(3.W))
        val i_scr1 = Input(UInt(23.W))
        val i_scr2 = Input(UInt(23.W))
        val i_E1 = Input(UInt(8.W))
        val i_E2 = Input(UInt(8.W))
        val i_signe = Input(UInt(1.W))
        val i_sign_diff = Input(Bool())
        val i_adr_des = Input(UInt(5.W))

        val o_alu = Output(UInt(23.W))
        val o_signe = Output(UInt(1.W))
        val o_exp = Output(UInt(8.W))
        val o_adr_des = Output(UInt(5.W))

    })

    /* Composants */
    val Alu = Module(new Alu(23))

    /* Connections */
    Alu.io.i_op := io.i_opM
    Alu.io.i_scr1 := io.i_scr1
    Alu.io.i_scr2 := io.i_scr2

    /* Programme */
    when(io.i_sign_diff){
        io.o_exp := io.i_E2
    } .otherwise {  io.o_exp := io.i_E1  }

    io.o_signe := io.i_signe + Alu.io.o_N
    io.o_adr_des := io.i_adr_des
}