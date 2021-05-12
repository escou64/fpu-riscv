package fpu

import chisel3._
import chisel3.util._
import chisel3.iotesters
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class ComparatorTest1(dut: Comparator) extends PeekPokeTester(dut){
  println("** ****************************************")
  println("**          BEGINNING TEST1 Comparator     ")
  println("** ****************************************")

  // first test
  // f1 = 4 > f2 = 2, prev_eq=0, prev_sup=1 ,prev_inf=0
  poke(dut.io.i_inf, 0)
  poke(dut.io.i_sup, 1)
  poke(dut.io.i_eq, 0)
  poke(dut.io.i_scr1, 4)
  poke(dut.io.i_scr2, 2)
  step(1)
  expect(dut.io.o_inf, 0)
  expect(dut.io.o_sup, 1)
  expect(dut.io.o_eq, 0)

  // Second test
  // f1 = 4 = f2 = 4, prev_eq=0, prev_sup=1 ,prev_inf=0
  poke(dut.io.i_inf, 0)
  poke(dut.io.i_sup, 1)
  poke(dut.io.i_eq, 0)
  poke(dut.io.i_scr1, 4)
  poke(dut.io.i_scr2, 4)
  step(1)
  expect(dut.io.o_inf, 0)
  expect(dut.io.o_sup, 1)
  expect(dut.io.o_eq, 0)

  // third test
  // f1 = 2 < f2 = 4, pprev_eq=0, prev_sup=1 ,prev_inf=0
  poke(dut.io.i_inf, 0)
  poke(dut.io.i_sup, 1)
  poke(dut.io.i_eq, 0)
  poke(dut.io.i_scr1, 2)
  poke(dut.io.i_scr2, 4)
  step(1)
  expect(dut.io.o_inf, 0)
  expect(dut.io.o_sup, 1)
  expect(dut.io.o_eq, 0)
}

// ComparatorTest1 execution (object)
object ComparatorTest1 extends App {
  iotesters.Driver.execute(args, () => new Comparator(3)) {
    dut => new ComparatorTest1(dut)
  }
}