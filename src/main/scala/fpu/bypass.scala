package fpu

import chisel3._
import chisel3.util._


class ByPass extends Module{
    val io = IO(new Bundle{
        //Inputs address register
        val i_addr_fs = Input(UInt(5.W))
        val i_addr_fd2 = Input(UInt(5.W))
        val i_addr_fd3 = Input(UInt(5.W))
        val i_addr_fd4 = Input(UInt(5.W))

        //Inputs value register
        val i_val_regfile = Input(UInt(32.W))
        val i_val_fd4 = Input(UInt(32.W))

        val i_en = Input(Bool())

        //Output
        val o_bypass = Output(UInt(32.W))
    })
    //New register
    val reg_out = Reg(Vec(32, UInt(32.W)))
    // ****************************************************
    //                      SELECTOR
    // ****************************************************
    
    when(io.i_addr_fs === io.i_addr_fd2){

            reg_out := 0xFFFFFFFF.U

        } .elsewhen(io.i_addr_fd3 === io.i_addr_fs){

           reg_out := 0xFFFFFFFF.U

        } .elsewhen(io.i_addr_fd4 === io.i_addr_fs){

           reg_out := io.i_val_fd4

        } .otherwise{ reg_out := io.i_val_regfile }

    when(io.i_en){
        io.o_bypass := reg_out
    }
}

