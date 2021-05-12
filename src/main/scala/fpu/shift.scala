package fpu

import chisel3._
import chisel3.util._

class Shift extends Module{
    val io = IO(new Bundle{

        /* Inputs */
        val i_opE = Input(UInt(3.W))
        val i_opM = Input(UInt(3.W))
        val i_E1 = Input(UInt(8.W))
        val i_E2 = Input(UInt(8.W))
        val i_M1 = Input(UInt(23.W))
        val i_M2 = Input(UInt(23.W))
        val i_adr_des = Input(UInt(5.W))
        val i_S = Input(UInt(1.W))
        val i_cd = Input(UInt(1.W))
        val i_infS = Input(UInt(1.W))
        val i_eqS = Input(UInt(1.W))
        val i_supS = Input(UInt(1.W))
        val i_writeEnable = Input(Bool())

        /* Ouputs */
        val o_M1 = Output(UInt(23.W))
        val o_M2 = Output(UInt(23.W))
        val o_Ne = Output(Bool())
        val o_adr_des = Output(UInt(5.W))
        val o_opM = Output(UInt(3.W))
        val o_E1 = Output(UInt(8.W))
        val o_E2 = Output(UInt(8.W))
        val o_S = Output(UInt(1.W))
        val o_infE = Output(UInt(1.W))
        val o_eqE = Output(UInt(1.W))
        val o_supE = Output(UInt(1.W))
        val o_writeEnable = Output(Bool())
        val o_cd = Output(UInt(1.W))
        val o_sign_diff = Output(UInt(8.W))


    })

    /* Composants */
    val Exp_alu = Module(new Alu(8))
    val Shifter = Module(new ShiftLR())

    //Module Input
    Exp_alu.io.i_op := io.i_opE
    Exp_alu.io.i_scr1 := io.i_E1
    Exp_alu.io.i_scr2 := io.i_E2
    Exp_alu.io.i_inf := io.i_infS
    Exp_alu.io.i_eq := io.i_eqS
    Exp_alu.io.i_sup := io.i_supS
    Shifter.io.i_signe := 0.U
    

    /* Programme */

    // Shifter exp
    when(io.i_cd === 1.U){
        Shifter.io.i_exp := Exp_alu.io.o_res
    }. otherwise{ Shifter.io.i_exp := 0.U}
    
    // Shifter mant
    when(Exp_alu.io.o_N === 0.U){
        Shifter.io.i_mant := io.i_M2
    }. otherwise { Shifter.io.i_mant := io.i_M1 }

    // Output connnections
    when(Exp_alu.io.o_N === 1.U){
        io.o_M1 := Shifter.io.o_mant
        io.o_M2 := io.i_M2
    }. otherwise{
        io.o_M1 := io.i_M1
        io.o_M2 := Shifter.io.o_mant
    }

    io.o_Ne := Exp_alu.io.o_N
    io.o_adr_des := io.i_adr_des
    io.o_S := io.i_S
    io.o_E1 := io.i_E1
    io.o_E2 := io.i_E2
    io.o_sign_diff := Exp_alu.io.o_res
    io.o_cd := io.i_cd
    io.o_writeEnable := io.i_writeEnable
    io.o_opM := io.i_opM
    io.o_infE := Exp_alu.io.o_inf
    io.o_eqE := Exp_alu.io.o_eq
    io.o_supE := Exp_alu.io.o_sup

    

}