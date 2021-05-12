package fpu

import chisel3._
import chisel3.util._
import chisel3.iotesters
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class FpuTest0(dut: Fpu) extends PeekPokeTester(dut){
  println("** ****************************************")
  println("**          BEGINNING TEST0 Fpu            ")
  println("** ****************************************")
  

}

// FpuTest0 execution (object)
object FpuTest0 extends App {
  iotesters.Driver.execute(args, () => new Fpu) {
    dut => new FpuTest0(dut)
  }
}