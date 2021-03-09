package fpu

import chisel3._
import chisel3.util._

/*
class Alu(nBit: Int) extends Module{
    val io = IO(new Bundle{
        //I/O listing
        val i_op = Input(UInt(3.W))
        val i_scr1 = Input(UInt(nBit.W))
        val i_scr2 = Input(UInt(nBit.W))
        val o_res = Output(UInt(nBit.W))
    })

    //New register
    val reg_res = Reg(UInt(nBit.W))

    //Operation- Register connection
    switch(io.i_op){
        is(0.U){
            reg_res := io.i_scr1 + io.i_scr2
        }
        is(1.U){
            reg_res := io.i_scr1 - io.i_scr2
        }
        is(4.U){
            if(io.i_scr1 < io.i_scr2){
                reg_res := io.i_scr1
            }
            else {reg_res := io.i_scr2 }
        }
        is(5.U){
            if(io.i_scr1 < io.i_scr2){
                reg_res := io.i_scr2
            }
            else {reg_res := io.i_scr1 }
        }
    }

    // Output connection
    reg_o_res := reg_res
}*/