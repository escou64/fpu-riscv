package fpu.example

import chisel3._
import chisel3.util._
import chisel3.iotesters
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}


class AdderTest0(dut : Adder) extends PeekPokeTester(dut) {
  println("** ****************************************")
  println("**          BEGINNING TEST0 ADDER          ")
  println("** ****************************************")
  // First test: 2 + 3 = 5
  poke(dut.io.i_src1, 2)
  poke(dut.io.i_src2, 3)
  step(1)
  expect(dut.io.o_res, 5)

  // Second test: 4 + 5 = 9
  poke(dut.io.i_src1, 5)
  poke(dut.io.i_src2, 4)
  step(1)
  expect(dut.io.o_res, 9)
}

// AdderTest0 execution (object)
object AdderTest0 extends App {
  iotesters.Driver.execute(args, () => new Adder(4)) {
    dut => new AdderTest0(dut)
  }
}
