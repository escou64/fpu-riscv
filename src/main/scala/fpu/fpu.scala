package fpu

import chisel3._
import chisel3.util._


class Fpu extends Module {
  val io = IO(new Bundle {
    val b_port = new FpuPortIO()
    val b_csr = new FpuCsrIO()
  })

  // ******************************
  //   TEMPORARY: MUST BE REMOVED
  // ******************************
  // ------------------------------
  //            REQUEST
  // ------------------------------
  io.b_port.req.ready := true.B

  // ------------------------------
  //         ACKNOWLEDGEMENT
  // ------------------------------
  io.b_port.commit.valid := true.B
  io.b_port.commit.res := 0.U

  // ------------------------------
  //              CSR
  // ------------------------------
  io.b_csr.nx := false.B
  io.b_csr.uf := false.B
  io.b_csr.of := false.B
  io.b_csr.dz := false.B
  io.b_csr.nv := false.B

  // ******************************
  //             DEBUG
  // ******************************
  dontTouch(io.b_port)
  dontTouch(io.b_csr)
}

object Fpu extends App {
  chisel3.Driver.execute(args, () => new Fpu())
}
