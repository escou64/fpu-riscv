package fpu

import chisel3._
import chisel3.util._

class Write extends Module{
    val io = IO(new Bundle{
        //Inputs
        val i_mant = Input(UInt(23.W))
        val i_signe = Input(UInt(1.W))
        val i_E = Input(UInt(8.W))
        val i_adr_des = Input(UInt(5.W))

        //Outputs
        val o_adr_des = Output(UInt(5.W))
        val o_data = Output(UInt(32.W))
        val o_writeEnable = Output(Bool())
        
    })

    val Normal = Module(new IncDec())
    val Shifter = Module(new ShiftLR())

   


    Normal.io.i_mant := io.i_mant
    Normal.io.i_E := io.i_E

    Shifter.io.i_mant := io.i_mant
    Shifter.io.i_signe := Normal.io.o_incDec
    Shifter.io.i_exp := Normal.io.o_nbre

    io.o_data := Cat(io.i_signe, Normal.io.o_E_norm, Shifter.io.o_mant)
    io.o_adr_des := io.i_adr_des
    io.o_writeEnable := true.B
}