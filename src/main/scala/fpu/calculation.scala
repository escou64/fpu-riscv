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
        val i_inf = Bool()
        val i_eq = Bool()
        val i_sup = Bool()

        val o_alu = Output(UInt(23.W))
        val o_signe = Output(UInt(1.W))
        val o_exp = Output(UInt(8.W))
        val o_adr_des = Output(UInt(5.W))
        val o_inf = Bool()
        val o_eq = Bool()
        val o_sup = Bool()

    })

    /* Composants */
    val Alu = Module(new Alu(23))

    /* Connections */
    Alu.io.i_op := io.i_opM
    Alu.io.i_scr1 := io.i_scr1
    Alu.io.i_scr2 := io.i_scr2
    Alu.io.i_inf := io.i_inf
    Alu.io.i_eq := io.i_eq
    Alu.io.i_sup := io.i_sup

    /* Programme */
    when(io.i_sign_diff){
        io.o_exp := io.i_E2
    } .otherwise {  io.o_exp := io.i_E1  }

    io.o_signe := io.i_signe + Alu.io.o_N
    io.o_adr_des := io.i_adr_des
    io.o_inf := Alu.io.o_inf
    io.o_eq := Alu.io.o_eq
    io.o_sup := Alu.io.o_sup
    
}