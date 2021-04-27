package fpu

import chisel3._
import chisel3.util._
import chisel3.iotesters
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class ComparatorTest0(dut: Comparator) extends PeekPokeTester(dut){
  println("** ****************************************")
  println("**          BEGINNING TEST0 Comparator     ")
  println("** ****************************************")

  // first test
  // f1 = 4 > f2 = 2, prev_eq=1, prev_sup=prev_inf=0
  poke(dut.io.i_inf, 0)
  poke(dut.io.i_sup, 0)
  poke(dut.io.i_eq, 1)
  poke(dut.io.i_scr1, 4)
  poke(dut.io.i_scr2, 2)
  step(1)
  expect(dut.io.o_inf, 0)
  expect(dut.io.o_sup, 1)
  expect(dut.io.o_eq, 0)

  // Second test
  // f1 = 4 = f2 = 4, prev_eq=1, prev_sup=prev_inf=0
  poke(dut.io.i_inf, 0)
  poke(dut.io.i_sup, 0)
  poke(dut.io.i_eq, 1)
  poke(dut.io.i_scr1, 4)
  poke(dut.io.i_scr2, 4)
  step(1)
  expect(dut.io.o_inf, 0)
  expect(dut.io.o_sup, 0)
  expect(dut.io.o_eq, 1)

  // third test
  // f1 = 2 = f2 = 4, prev_eq=1, prev_sup=prev_inf=0
  poke(dut.io.i_inf, 0)
  poke(dut.io.i_sup, 0)
  poke(dut.io.i_eq, 1)
  poke(dut.io.i_scr1, 2)
  poke(dut.io.i_scr2, 4)
  step(1)
  expect(dut.io.o_inf, 1)
  expect(dut.io.o_sup, 0)
  expect(dut.io.o_eq, 0)
}

// Comparatorest0 execution (object)
object ComparatorTest0 extends App {
  iotesters.Driver.execute(args, () => new Comparator(3)) {
    dut => new ComparatorTest0(dut)
  }
}