package fpu

import chisel3._
import chisel3.util._
import chisel3.iotesters
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class ShiftTest0(dut: Shift) extends PeekPokeTester(dut){
  println("** ****************************************")
  println("**          BEGINNING TEST0 Shift          ")
  println("** ****************************************")
  
  poke(dut.io.i_M1, "b00110101000000000000000".U)
  poke(dut.io.i_M2, "b00010101000110000000000".U)
  poke(dut.io.i_E1, "b01010101".U)
  poke(dut.io.i_E2, "b00010001".U)
  poke(dut.io.i_S, 0)
  poke(dut.io.i_infS,0)
  poke(dut.io.i_eqS, 1)
  poke(dut.io.i_supS, 0)
  poke(dut.io.i_opM, FPUUOP.ADD)
  poke(dut.io.i_opE, FPUUOP.SUB)
  poke(dut.io.i_cd, 1)
  poke(dut.io.i_adr_des, 3)
  poke(dut.io.i_writeEnable, true.B)
  step(1)
  expect(dut.io.o_M1, "b00110101000000000000000".U)
  expect(dut.io.o_M2, "b00000000000000000000000".U)
  expect(dut.io.o_Ne, false.B)
  expect(dut.io.o_adr_des, 3)
  expect(dut.io.o_opM, FPUUOP.ADD)
  expect(dut.io.o_E1, "b01010101".U)
  expect(dut.io.o_E2, "b00010001".U)
  expect(dut.io.o_S, 0)
  expect(dut.io.o_infE,0)
  expect(dut.io.o_supE, 1)
  expect(dut.io.o_eqE, 0)
  expect(dut.io.o_writeEnable, true.B)
  expect(dut.io.o_cd, 1)
  expect(dut.io.o_sign_diff, "b01000100".U)
}

// ShiftTest0 execution (object)
object ShiftTest0 extends App {
  iotesters.Driver.execute(args, () => new Shift) {
    dut => new ShiftTest0(dut)
  }
}