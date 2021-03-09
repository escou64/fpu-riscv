package fpu

import chisel3._
import chisel3.util._


class ReadRegFileIO extends Bundle{
    val addr = Input(UInt(5.W))
    val data = Output(UInt(32.W))
}

class WriteRegFileIO extends Bundle{
    val en = Input(Bool())
    val addr = Input(UInt(5.W))
    val data = Input(UInt(32.W))
}

class RegFile(p : RegFileParams) extends Module{
    val io = IO(new Bundle {
        val b_rport = Vec(p.nRegFileRead, new ReadRegFileIO)
        val b_wport = Vec(p.nRegFileWrite, new WriteRegFileIO)
    })

    //List register
    val reg_regfile = Reg(Vec(32, UInt(32.W)))

    // ****************************************************
    //                      WRITE
    // ****************************************************
    for(w <- 0 until p.nRegFileWrite){
        when(io.b_wport(w).en){
            reg_regfile(io.b_wport(w).addr) := io.b_wport(w).data
        }
    }

     // ****************************************************
    //                      READ
    // ****************************************************
    for(r <- 0 until p.nRegFileRead){
        io.b_rport(r).data := reg_regfile(io.b_rport(r).addr) 
    }

}




// Adder Verilog generator (object)
object RegFile extends App {
  chisel3.Driver.execute(args, () => new RegFile(RegFileDefault))
}