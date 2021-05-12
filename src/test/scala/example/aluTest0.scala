package fpu

import chisel3._
import chisel3.util._
import chisel3.iotesters
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class AluTest0(dut: Alu) extends PeekPokeTester(dut){
  println("** ****************************************")
  println("**          BEGINNING TEST0 Alu            ")
  println("** ****************************************")
    
  // first test
  // opération d'addition => 1 + 3 = 4
  poke(dut.io.i_op, FPUUOP.ADD)
  poke(dut.io.i_inf, 0)
  poke(dut.io.i_sup, 0)
  poke(dut.io.i_eq, 1)
  poke(dut.io.i_scr1, 1)
  poke(dut.io.i_scr2, 3)
  step(1)
  expect(dut.io.o_res, 4)
  expect(dut.io.o_inf, 1)
  expect(dut.io.o_sup, 0)
  expect(dut.io.o_eq, 0)
  expect(dut.io.o_N, 0)

  // Second test
  // opération de soustraction => 3 - 2 = 1
  poke(dut.io.i_op, FPUUOP.SUB)
  poke(dut.io.i_inf, 0)
  poke(dut.io.i_sup, 0)
  poke(dut.io.i_eq, 1)
  poke(dut.io.i_scr1, 3)
  poke(dut.io.i_scr2, 2)
  step(1)
  expect(dut.io.o_res, 1)
  expect(dut.io.o_inf, 0)
  expect(dut.io.o_sup, 1)
  expect(dut.io.o_eq, 0)
  expect(dut.io.o_N, 0)

  // Third test
  // opération de soustraction => 1 - 4 = - 3
  poke(dut.io.i_op, FPUUOP.SUB)
  poke(dut.io.i_inf, 0)
  poke(dut.io.i_sup, 0)
  poke(dut.io.i_eq, 1)
  poke(dut.io.i_scr1, 1)
  poke(dut.io.i_scr2, 4)
  step(1)
  expect(dut.io.o_res, 3)
  expect(dut.io.o_inf, 1)
  expect(dut.io.o_sup, 0)
  expect(dut.io.o_eq, 0)
  expect(dut.io.o_N, 1)

  // Fourth test
  // opération de minimum => scr1 > scr2
  poke(dut.io.i_op, FPUUOP.MIN)
  poke(dut.io.i_inf, 0)
  poke(dut.io.i_sup, 0)
  poke(dut.io.i_eq, 1)
  poke(dut.io.i_scr1, 8)
  poke(dut.io.i_scr2, 3)
  step(1)
  expect(dut.io.o_res, 3)
  expect(dut.io.o_inf, 0)
  expect(dut.io.o_sup, 1)
  expect(dut.io.o_eq, 0)
  expect(dut.io.o_N, 0)

  // Fifth test
  // opération de minimum => scr1 < scr2
  poke(dut.io.i_op, FPUUOP.MIN)
  poke(dut.io.i_inf, 0)
  poke(dut.io.i_sup, 0)
  poke(dut.io.i_eq, 1)
  poke(dut.io.i_scr1, 5)
  poke(dut.io.i_scr2, 6)
  step(1)
  expect(dut.io.o_res, 5)
  expect(dut.io.o_inf, 1)
  expect(dut.io.o_sup, 0)
  expect(dut.io.o_eq, 0)
  expect(dut.io.o_N, 0)


  // Sixth test
  // opération de maximum => scr1 > scr2
  poke(dut.io.i_op, FPUUOP.MAX)
  poke(dut.io.i_inf, 0)
  poke(dut.io.i_sup, 0)
  poke(dut.io.i_eq, 1)
  poke(dut.io.i_scr1, 6)
  poke(dut.io.i_scr2, 3)
  step(1)
  expect(dut.io.o_res, 6)
  expect(dut.io.o_inf, 0)
  expect(dut.io.o_sup, 1)
  expect(dut.io.o_eq, 0)
  expect(dut.io.o_N, 0)

  // Seventh test
  // opération de maximum => scr1 < scr2
  poke(dut.io.i_op, FPUUOP.MAX)
  poke(dut.io.i_inf, 0)
  poke(dut.io.i_sup, 0)
  poke(dut.io.i_eq, 1)
  poke(dut.io.i_scr1, 5)
  poke(dut.io.i_scr2, 6)
  step(1)
  expect(dut.io.o_res, 6)
  expect(dut.io.o_inf, 1)
  expect(dut.io.o_sup, 0)
  expect(dut.io.o_eq, 0)
  expect(dut.io.o_N, 0)


}

// AluTest0 execution (object)
object AluTest0 extends App {
  iotesters.Driver.execute(args, () => new Alu(4)) {
    dut => new AluTest0(dut)
  }
}