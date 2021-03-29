package fpu

import chisel3._
import chisel3.util._


class Alu(nBit: Int) extends Module{
    val io = IO(new Bundle{
        //I/O listing
        val i_op = Input(UInt(3.W))
        val i_scr1 = Input(UInt(nBit.W))
        val i_scr2 = Input(UInt(nBit.W))
        val o_res = Output(UInt(nBit.W))
        val o_N = Output(Bool())        //1 si le resultat est négatif, O sinon
    })

    //New register
    val reg_res = Wire(UInt(nBit.W))
    reg_res := 0.U

    //Operation- Register connection
    

    switch(io.i_op){
        is(FPUUOP.ADD.U){
            reg_res := io.i_scr1 + io.i_scr2
            io.o_N := 0.U
        }
        is(FPUUOP.SUB.U){
            when(io.i_scr1 > io.i_scr2){
                reg_res := io.i_scr1 - io.i_scr2
                io.o_N := 0.U
            } .otherwise{
                reg_res := io.i_scr2 - io.i_scr1
                io.o_N := 1.U
            }
        }
        is(FPUUOP.MIN.U){
            when(io.i_scr1 < io.i_scr2){
                reg_res := io.i_scr1
            } .otherwise {reg_res := io.i_scr2 }
        }
        is(FPUUOP.MAX.U){
            when(io.i_scr1 < io.i_scr2){
                reg_res := io.i_scr2
            } 
        }
    }

    // Output connection
    io.o_res := reg_res
}