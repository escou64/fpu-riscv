package fpu

import chisel3._
import chisel3.util._


/*class ByPass extends Module{
    val io = IO(new Bundle({
        //Inputs address register
        val i_addr_fs = Input(UInt(5.W))
        val i_addr_fd2 = Input(UInt(5.W))
        val i_addr_fd3 = Input(UInt(5.W))
        val i_addr_fd4 = Input(UInt(5.W))

        //Inputs value register
        val i_val_regfile = Input(UInt(32.w))
        val i_val_fd3 = Input(UInt(32.w))
        val i_val_fd4 = Input(UInt(32.w))

        val i_en = Input(Bool())

        //Output
        val o_bypass = Output(UInt(32.))
    })

    //New register
    val reg_out = Reg(UInt(32.W))
    // ****************************************************
    //                      SELECTOR
    // ****************************************************
    
    switch(i_addr_fs){
        is(i_addr_fd2){
            reg_out := 0xFFFFFFFF.w
        }
        is(i_addr_fd3){
           reg_out := i_val_fd3
        }
        is(i_addr_fd4){
           reg_out := i_val_fd4
        }
       is(){

       }
    }

    when(i_en){
        reg := 
    }
}*/


