package fpu

import chisel3._
import chisel3.util._

class Shift extends Module{
    val io = IO(new Bundle{

        /* Inputs */
        val i_en = Input(Bool())
        val i_opE = Input(UInt(3.W))
        val i_S1 = Input(Bool())
        val i_S2 = Input(Bool())
        val i_E1 = Input(SInt(8.W))
        val i_E2 = Input(SInt(8.W))
        val i_M1 = Input(UInt(23.W))
        val i_M2 = Input(UInt(23.W))
        val i_adr_des = Input(UInt(5.W))

        /* Ouputs */
        val o_op1 = Output(UInt(23.W))
        val o_op2 = Output(UInt(23.W))
        val o_sign_diff = Output(Bool())
        val o_adr_des = Output(UInt(5.W))
    })

   /* Composants */
    val Exp_alu = Module(new AluExposant())
    val Shifter = Module(new ShiftLR())

    //Module Input
    Exp_alu.io.i_op := io.i_opE
    Exp_alu.io.i_scr1 := io.i_E1
    Exp_alu.io.i_scr2 := io.i_E2

    Shifter.io.i_signe := 0.U
    Shifter.io.i_exp := Exp_alu.io.o_res

    //Programme
    when(Exp_alu.io.o_N){
        Shifter.io.i_mant := io.i_M2
    }. otherwise { Shifter.io.i_mant := io.i_M1 }


    when(Exp_alu.io.o_N){
        io.o_op1 := Shifter.io.o_mant
        io.o_op2 := io.i_M2
    }. otherwise{
        io.o_op1 := io.i_M1
        io.o_op2 := Shifter.io.o_mant
    }
    
    io.o_sign_diff := Exp_alu.io.o_N
    io.o_adr_des := io.i_adr_des

}