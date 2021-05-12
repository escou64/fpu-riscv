package fpu

import chisel3._
import chisel3.util._
import chisel3.iotesters
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class BypassTest0(dut: ByPass) extends PeekPokeTester(dut){
  println("** ****************************************")
  println("**          BEGINNING TEST0 Bypass         ")
  println("** ****************************************")
  
  //first step
  poke(dut.io.i_addr_fs, 1)
  poke(dut.io.i_addr_fd2, 22)
  poke(dut.io.i_addr_fd3, 12)
  poke(dut.io.i_addr_fd4, 5)
  poke(dut.io.i_val_regfile, 34)
  poke(dut.io.i_val_fd4, 45)
  step(1)
  expect(dut.io.o_bypass, 34)

  //second step
  poke(dut.io.i_addr_fs, 4)
  poke(dut.io.i_addr_fd2, 4)
  poke(dut.io.i_addr_fd3, 6)
  poke(dut.io.i_addr_fd4, 13)
  poke(dut.io.i_val_regfile, 58)
  poke(dut.io.i_val_fd4, 23)
  step(1)
  expect(dut.io.o_bypass, 0x7FC00000.U)

  //third step
  poke(dut.io.i_addr_fs, 13)
  poke(dut.io.i_addr_fd2, 4)
  poke(dut.io.i_addr_fd3, 6)
  poke(dut.io.i_addr_fd4, 13)
  poke(dut.io.i_val_regfile, 58)
  poke(dut.io.i_val_fd4, 23)
  step(1)
  expect(dut.io.o_bypass, 23)
}

// BypassTest0 execution (object)
object BypassTest0 extends App {
  iotesters.Driver.execute(args, () => new ByPass()) {
    dut => new BypassTest0(dut)
  }
}