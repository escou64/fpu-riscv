package fpu

import chisel3._
import chisel3.util._

class decode extends Module{
    val io = IO(new Bundle{
        val opcode = Input (UInt(3.W))
        val i_S1 = Input (UInt(1.W))
        val i_S2 = Input(UInt(1.W))

        val i_opE = Output(UInt(3.W))
        val i_opM = Output(UInt(3.W))
       
    })
    
    when (FPUUOP.ADD.U){
        if(io.i_S1 = io.i_S2){
           io.i_opM  := FPUUOP.ADD.U
        }
        else if (io.i_S1 < io.i_S2){
            io.i_opM  := FPUUOP.SUB.U
            io.i_opE  := FPUUOP.SUB.U
        }
        else if (io.i_S1 > io.i_S2){
            io.i_opM  := FPUUOP.SUB.U
            io.i_opE  := FPUUOP.SUB.U

        }
    }

     when (FPUUOP.SUB.U){
        if(io.i_S1 = io.i_S2){
           io.i_opM  := FPUUOP.SUB.U
           io.i_opE  := FPUUOP.SUB.U
        }
        else if (io.i_S1 < io.i_S2){
            io.i_opM  := FPUUOP.ADD.U
        }
        else if (io.i_S1 > io.i_S2){
            io.i_opM  := FPUUOP.SUB.U
            io.i_opE  := FPUUOP.SUB.U

        }
    }




 
}
