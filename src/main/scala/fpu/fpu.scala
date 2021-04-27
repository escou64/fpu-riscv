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

  // ******************************
  //          Structure
  // ******************************

  val SLecture = Module(new Read)
  val SDecalage = Module(new Shift)
  val SCalcul = Module(new Calculation)
  val SEcriture = Module(new Write)
  val RegisterFile = Module(new RegFile(RegFileDefault))
  val ByPass = Module(new ByPass)


  /* Register File */
  //Ecriture
  when(RegisterFile.io.b_wport(0).en & SEcriture.io.o_writeEnable){
    RegisterFile.io.b_wport(0).addr := SEcriture.io.o_adr_des
    RegisterFile.io.b_wport(0).data := SEcriture.io.o_data
  }

  //Lecture
  RegisterFile.io.b_rport(0).addr := io.b_port.req.fs1
  RegisterFile.io.b_rport(2).addr := io.b_port.req.fs2
    

  /* ByPass */
  ByPass.io.i_addr_fs := io.b_port.req.fd
  ByPass.io.i_addr_fd2 := SDecalage.io.i_adr_des
  ByPass.io.i_addr_fd3 := SCalcul.io.i_adr_des
  ByPass.io.i_addr_fd4 := SEcriture.io.i_adr_des
  ByPass.io.i_val_regfile := RegisterFile.io.b_rport(0).data //a modif
  ByPass.io.i_val_fd4 := SEcriture.io.o_data
    

  /* Connection entre les étages */
  // lecture
  SLecture.io.i_f1 := RegisterFile.io.b_rport(0).data
  SLecture.io.i_f2 := RegisterFile.io.b_rport(1).data
  SLecture.io.i_instruction := io.b_port.req.uop
  SLecture.io.i_adr_des := io.b_port.req.fd

  // Decalage
  SDecalage.io.i_opE := SLecture.io.o_opE
  SDecalage.io.i_opM := SLecture.io.o_opM
  SDecalage.io.i_cd := SLecture.io.o_cd
  SDecalage.io.i_adr_des := SLecture.io.o_adr_des
  SDecalage.io.i_infS := SLecture.io.o_SInf
  SDecalage.io.i_eqS := SLecture.io.o_SEq
  SDecalage.io.i_supS := SLecture.io.o_SSup
  SDecalage.io.i_M1 := SLecture.io.o_M1
  SDecalage.io.i_M2 := SLecture.io.o_M2
  SDecalage.io.i_E1 := SLecture.io.o_E1
  SDecalage.io.i_E2 := SLecture.io.o_E2
  SDecalage.io.i_S := SLecture.io.o_S
  SDecalage.io.i_writeEnable := SLecture.io.o_writeEnable

  // Calcul
  SCalcul.io.i_opM := SDecalage.io.o_opM
  SCalcul.io.i_scr1 := SDecalage.io.o_M1
  SCalcul.io.i_scr2 := SDecalage.io.o_M2
  SCalcul.io.i_E1 := SDecalage.io.o_E1
  SCalcul.io.i_E2 := SDecalage.io.o_E2
  SCalcul.io.i_signe := SDecalage.io.o_S
  SCalcul.io.i_sign_diff := SDecalage.io.o_sign_diff
  SCalcul.io.i_Ne := SDecalage.io.o_Ne
  SCalcul.io.i_adr_des := SDecalage.io.o_adr_des
  SCalcul.io.i_cd :=  SDecalage.io.o_cd
  SCalcul.io.i_inf := SDecalage.io.o_infE
  SCalcul.io.i_eq := SDecalage.io.o_eqE
  SCalcul.io.i_sup := SDecalage.io.o_supE
  SCalcul.io.i_writeEnable := SDecalage.io.o_writeEnable

  // Ecriture 
  SEcriture.io.i_mant :=  SCalcul.io.o_alu
  SEcriture.io.i_signe := SCalcul.io.o_signe
  SEcriture.io.i_E := SCalcul.io.o_exp
  SEcriture.io.i_adr_des := SCalcul.io.o_adr_des
  SEcriture.io.i_writeEnable := SCalcul.io.o_writeEnable

}
object Fpu extends App {
  chisel3.Driver.execute(args, () => new Fpu())
}
