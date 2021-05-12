package fpu

import chisel3._
import chisel3.util._
import chisel3.iotesters
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class CalculationTest0(dut: Calculation) extends PeekPokeTester(dut){
  println("** ****************************************")
  println("**        BEGINNING TEST0 Calculation      ")
  println("** ****************************************")
  
  poke(dut.io.i_opM, FPUUOP.ADD)
  poke(dut.io.i_scr1, "b00110101000000000000000".U)
  poke(dut.io.i_scr2, "b00000000000000000000000".U)
  poke(dut.io.i_E1, "b01010101".U)
  poke(dut.io.i_E2, "b00010001".U)
  poke(dut.io.i_signe, 0)
  poke(dut.io.i_sign_diff, "b01000100".U)
  poke(dut.io.i_Ne, 0)
  poke(dut.io.i_adr_des, 3)
  poke(dut.io.i_cd, 1)
  poke(dut.io.i_inf, 0)
  poke(dut.io.i_eq, 0)
  poke(dut.io.i_sup, 1)
  poke(dut.io.i_writeEnable, true.B)
  step(1)
  expect(dut.io.o_alu, "b00110101000000000000000".U)
  expect(dut.io.o_signe, 0)
  expect(dut.io.o_exp, "b01010101".U)
  expect(dut.io.o_adr_des, 3)
  expect(dut.io.o_writeEnable, true.B)

}

// CalculationTest0 execution (object)
object CalculationTest0 extends App {
  iotesters.Driver.execute(args, () => new Calculation) {
    dut => new CalculationTest0(dut)
  }
}