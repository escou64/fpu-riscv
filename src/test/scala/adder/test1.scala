package fpu.tools

import chisel3._
import chisel3.util._
import chisel3.iotesters
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}


class AdderTest1(dut : Adder) extends PeekPokeTester(dut) {
  println("** ****************************************")
  println("**          BEGINNING TEST1 ADDER          ")
  println("** ****************************************")
  // First test: 64 + 96 = 160
  poke(dut.io.i_src1, 64)
  poke(dut.io.i_src2, 96)
  step(1)
  expect(dut.io.o_res, 160)

  // Second test: 256 + 256 = 512
  poke(dut.io.i_src1, 256)
  poke(dut.io.i_src2, 256)
  step(1)
  // Fail
  expect(dut.io.o_res, 513)

  // Third test: 256 + 256 = 512
  poke(dut.io.i_src1, 256)
  poke(dut.io.i_src2, 256)
  step(1)
  expect(dut.io.o_res, 512)
}

// AdderTest1 execution (object)
object AdderTest1 extends App {
  iotesters.Driver.execute(args, () => new Adder(32)) {
    dut => new AdderTest0(dut)
  }
}
