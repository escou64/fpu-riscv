package fpu

import chisel3._
import chisel3.util._

class Shift extends Module{
    val io = IO(new Bundle{

        /* Inputs */
        val i_en = Input(Bool())
        val i_opE = Input(UInt(3.W))
        val i_opM = Input(UInt(3.W))
        val i_scr1 = Input(Bool())
        val i_scr2 = Input(Bool())
        val i_E1 = Input(SInt(8.W))
        val i_E2 = Input(SInt(8.W))
        val i_M1 = Input(UInt(23.W))
        val i_M2 = Input(UInt(23.W))

        val i_adr_des = Input(UInt(5.W))

        /* Ouputs */
        val o_scr1 = Output(UInt(23.W))
        val o_scr2 = Output(UInt(23.W))
        val o_sign_diff = Output(Bool())
        val o_adr_des = Output(UInt(5.W))
        val o_opM = Output(UInt(3.W))
        val o_E1 = Output(UInt(8.W))
        val o_E2 = Output(UInt(8.W))
        val o_signe = Output(UInt(1.W))
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
        io.o_scr1 := Shifter.io.o_mant
        io.o_scr2 := io.i_M2
    }. otherwise{
        io.o_scr1 := io.i_M1
        io.o_scr2 := Shifter.io.o_mant
    }
    
    io.o_sign_diff := Exp_alu.io.o_N
    io.o_adr_des := io.i_adr_des
    io.o_signe := Exp_alu.io.o_N

}