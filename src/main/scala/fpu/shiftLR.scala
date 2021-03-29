package fpu

import chisel3._
import chisel3.util._

import chisel3.core.FixedPoint
import chisel3.internal.firrtl.BinaryPoint



class ShiftLR extends Module{
    val io = IO(new Bundle{
        //Inputs
        val i_signe = Input(UInt(1.W))     //0 si positive (true) et 1 si négative(false)
        val i_exp = Input(UInt(8.W))
        val i_mant = Input(UInt(23.W))

        //Output
        val o_mant = Output(UInt(23.W))
    })

    val res = io.i_mant
    

    when(io.i_signe === 1.U){
        res := io.i_exp << io.i_mant
    } .otherwise{
        res := io.o_mant >> io.i_exp
    }

    io.o_mant := res

}