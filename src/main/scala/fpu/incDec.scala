package fpu

import chisel3._
import chisel3.util._

class IncDec extends Module{
    val io = IO(new Bundle{
        val i_mant = Input(UInt(23.W))
        val i_E = Input(UInt(8.W))

        val o_incDec = Output(UInt(1.W))   //0 si incremente, 1 si décrémente
        val o_nbre = Output(UInt(8.W))
        val o_E_norm = Output(UInt(8.W))
    })

    val mant = Wire(UInt(23.W))
    mant := io.i_mant
    mant := Reverse(mant)

    val position = Wire(UInt(8.W))
    position := PriorityEncoder(mant)

    when(io.i_mant >= 10.U){
        io.o_incDec := 0.U
        io.o_nbre := position
        io.o_E_norm := io.i_E + position
    }. elsewhen(io.i_mant < 1.U){
        io.o_incDec := 1.U
        io.o_nbre := 1.U
        io.o_E_norm := io.i_E - position
    }. otherwise{ 
        io.o_incDec := 0.U
        io.o_nbre := 0.U
        io.o_E_norm := io.i_E
    }

}