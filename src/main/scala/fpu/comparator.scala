package fpu

import chisel3._
import chisel3.util._

class Comparator(nBit: Int) extends Module{
    val io = IO(new Bundle{
        val i_scr1 = Input(UInt(nBit.W))
        val i_scr2 = Input(UInt(nBit.W))
        val i_inf = Bool()
        val i_eq = Bool()
        val i_sup = Bool()

        val o_inf = Bool()
        val o_eq = Bool()
        val o_sup = Bool()
    })

    when((io.i_eq === true.B) & (io.i_inf === false.B) & (io.i_sup === false.B)){
        when(io.i_scr1 < io.i_scr2){
            io.o_sup := true.B
            io.o_eq := false.B
            io.o_inf := false.B
        }. elsewhen(io.i_scr1 < io.i_scr2) {
            io.o_sup := false.B
            io.o_eq := false.B
            io.o_inf := true.B
        }. otherwise{
            io.o_sup := false.B
            io.o_eq := true.B
            io.o_inf := false.B
        }
    }. elsewhen((io.i_eq === false.B) & (io.i_inf === true.B) & (io.i_sup === false.B)){
        io.o_sup := false.B
        io.o_eq := false.B
        io.o_inf := true.B
    }. elsewhen((io.i_eq === false.B) & (io.i_inf === false.B) & (io.i_sup === true.B)){
        io.o_sup := false.B
        io.o_eq := false.B
        io.o_inf := true.B
    }
}