package fpu.tools

import chisel3._
import chisel3.util._


// Adder class
class Adder(nBit: Int) extends Module {
  val io = IO(new Bundle {
    // I/Os listing
    val i_src1 = Input(UInt(nBit.W))
    val i_src2 = Input(UInt(nBit.W))
    val o_res = Output(UInt(nBit.W))
  })

  // New register
  val reg_res = Reg(UInt(nBit.W))

  // Register connection
  reg_res := io.i_src1 + io.i_src2

  // Output connection
  io.o_res := reg_res
}

// Adder Verilog generator (object)
object Adder extends App {
  chisel3.Driver.execute(args, () => new Adder(5))
}

object Adder8 extends App {
  chisel3.Driver.execute(args, () => new Adder(8))
}
