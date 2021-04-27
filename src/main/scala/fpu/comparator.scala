package fpu

import chisel3._
import chisel3.util._

class Comparator(nBit: Int) extends Module{
    val io = IO(new Bundle{
        val i_scr1 = Input(UInt(nBit.W))
        val i_scr2 = Input(UInt(nBit.W))
        val i_inf = Input(UInt(1.W))
        val i_eq = Input(UInt(1.W))
        val i_sup = Input(UInt(1.W))

        val o_inf = Output(UInt(1.W))
        val o_eq = Output(UInt(1.W))
        val o_sup = Output(UInt(1.W))
    })

    when((io.i_eq === 1.U) & (io.i_inf === 0.U) & (io.i_sup === 0.U)){
        when(io.i_scr1 > io.i_scr2){
            io.o_sup := 1.U
            io.o_eq := 0.U
            io.o_inf := 0.U
        }. elsewhen(io.i_scr1 < io.i_scr2) {
            io.o_sup := 0.U
            io.o_eq := 0.U
            io.o_inf := 1.U
        }. otherwise{
            io.o_sup := 0.U
            io.o_eq := 1.U
            io.o_inf := 0.U
        }
    }. elsewhen((io.i_eq === 0.U) & (io.i_inf === 1.U) & (io.i_sup === 0.U)){
        io.o_sup := 0.U
        io.o_eq := 0.U
        io.o_inf := 1.U
    }. elsewhen((io.i_eq === 0.U) & (io.i_inf === 0.U) & (io.i_sup === 1.U)){
        io.o_sup := 1.U
        io.o_eq := 0.U
        io.o_inf :=0.U
    }. otherwise{
    	io.o_sup := 0.U
        io.o_eq := 0.U
        io.o_inf := 0.U
    }
    
}
