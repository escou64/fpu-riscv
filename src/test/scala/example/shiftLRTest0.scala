package fpu

import chisel3._
import chisel3.util._
import chisel3.iotesters
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class ShiftLRTest0(dut: ShiftLR) extends PeekPokeTester(dut){
  println("** ****************************************")
  println("**          BEGINNING TEST0 ShiftLR        ")
  println("** ****************************************")
  
  //first step
  //mant = 50, exp = 1, signe = 0 => res = 25
  poke(dut.io.i_mant, 50)
  poke(dut.io.i_exp, 1)
  poke(dut.io.i_signe, 0)
  step(1)
  expect(dut.io.o_mant, 25)

  //second step
  poke(dut.io.i_mant, 25)
  step(1)
  expect(dut.io.o_mant, 12)

  //third step
  poke(dut.io.i_mant, 12)
  poke(dut.io.i_exp, 2)
  poke(dut.io.i_signe, 1)
  step(1)
  expect(dut.io.o_mant, 48)

}

// ShiftLRTest0 execution (object)
object ShiftLRTest0 extends App {
  iotesters.Driver.execute(args, () => new ShiftLR()) {
    dut => new ShiftLRTest0(dut)
  }
}