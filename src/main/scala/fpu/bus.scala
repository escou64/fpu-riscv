package fpu

import chisel3._
import chisel3.util._


// ******************************
//             PORT
// ******************************
class FpuReqIO extends Bundle {
  val ready = Output(Bool())
  val valid = Input(Bool())
  val pc = Input(UInt(32.W))
  val uop = Input(UInt(FPUUOP.NBIT.W))
  val s1 = Input(UInt(32.W))
  val imm = Input(UInt(32.W))
  val round = Input(UInt(FPUROUND.NBIT.W))
  val fs1 = Input(UInt(5.W))
  val fs2 = Input(UInt(5.W))
  val fs3 = Input(UInt(5.W))
  val fd = Input(UInt(5.W))
}

class FpuCommitIO extends Bundle {
  val ready = Input(Bool())
  val valid = Output(Bool())
  val res = Output(UInt(32.W))
}

class FpuPortIO extends Bundle {
  val req = new FpuReqIO()
  val commit = new FpuCommitIO()
}

// ******************************
//             CSR
// ******************************
class FpuCsrIO extends Bundle {
  val nx = Output(Bool())
  val uf = Output(Bool())
  val of = Output(Bool())
  val dz = Output(Bool())
  val nv = Output(Bool())
  val round = Input(UInt(FPUROUND.NBIT.W))
}