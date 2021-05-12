package fpu

import chisel3._
import chisel3.util._
import chisel3.iotesters
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class RegFileTest0(dut: RegFile) extends PeekPokeTester(dut){
  println("** ****************************************")
  println("**          BEGINNING TEST0 RegFile        ")
  println("** ****************************************")

  //first step
  //DATA(0) = 0 00001101 11100100000000000000000(b)= 116523008(d) 
  poke(dut.io.b_wport(0).addr, 2.U)
  poke(dut.io.b_wport(0).data, 116523008.U)
  poke(dut.io.b_wport(0).en, 0.U)
  poke(dut.io.b_rport(0).addr, 2.U)
  poke(dut.io.b_rport(1).addr, 36.U)
  step(1)
  expect(dut.io.b_rport(0).data, 0.U)
  expect(dut.io.b_rport(1).data, 0.U)

  //second step
  poke(dut.io.b_wport(0).en, 1.U)
  step(1)
  expect(dut.io.b_rport(0).data, 116523008.U)
  expect(dut.io.b_rport(1).data, 0.U)

  //third step
  //DATA(1) = 1 00100001 01100100001100000100000(b)= 280107040(d)
  poke(dut.io.b_wport(0).addr, 36.U)
  poke(dut.io.b_wport(0).data, 280107040.U)
  step(1)
  expect(dut.io.b_rport(0).data, 116523008.U)
  expect(dut.io.b_rport(1).data, 280107040.U)

}

// RegFileTest0 execution (object)
object RegFileTest0 extends App {
  iotesters.Driver.execute(args, () => new RegFile(RegFileDefault)) {
    dut => new RegFileTest0(dut)
  }
}