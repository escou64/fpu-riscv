package fpu

import chisel3._
import chisel3.util._
import chisel3.iotesters
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class IncDecTest0(dut: IncDec) extends PeekPokeTester(dut){
  println("** ****************************************")
  println("**          BEGINNING TEST0 IncDec         ")
  println("** ****************************************")
  
  //first step
  poke(dut.io.i_mant, "b00000000000000000000000".U)
  poke(dut.io.i_E, "b00000001".U)
  step(1)
  



}

// IncDecTest0 execution (object)
object IncDecTest0 extends App {
  iotesters.Driver.execute(args, () => new IncDec) {
    dut => new IncDecTest0(dut)
  }
}