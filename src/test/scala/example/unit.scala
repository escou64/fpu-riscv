package fpu.example

import chisel3._
import chisel3.util._
import chisel3.iotesters
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}


// Class for unit testing
class UnitTester extends ChiselFlatSpec {
  // Module test here (multiple in on class is possible)
  behavior of "Adder"

  // First unit test
  it should "test some sequences of addition: nBit = 4" in {
    Driver.execute(Array("--generate-vcd-output", "on", "--target-dir", "output/", "--is-verbose"),
    () => new Adder(4)) {
      c => new AdderTest0(c)
    } should be (true)
  }

  // Second unit test
  it should "test some sequences of addition: nBit = 8" in {
    Driver.execute(Array("--generate-vcd-output", "on", "--target-dir", "output/", "--is-verbose"),
    () => new Adder(8)) {
      c => new AdderTest0(c)
    } should be (true)
  }

  // Third unit test
  it should "test new sequences of addition: nBit = 32" in {
    Driver.execute(Array("--generate-vcd-output", "on", "--target-dir", "output/", "--is-verbose"),
    () => new Adder(32)) {
      c => new AdderTest1(c)
    } should be (true)
  }
}
