package fpu

import chisel3._
import chisel3.util._

class Read extends Module{
    val io = IO(new Bundle{
       // Inputs
        val i_f1 = Input(UInt(32.W))
        val i_f2 = Input(UInt(32.W))
        val i_instruction = Input(UInt(3.W))
        val i_adr_des = Input(UInt(5.W))

       // Outputs
        val o_M1 = Output(UInt(23.W))
        val o_M2 = Output(UInt(23.W))
        val o_E1 = Output(UInt(8.W))
        val o_E2 = Output(UInt(8.W))
        val o_S = Output(UInt(1.W))
        val o_SInf = Output(UInt(1.W))
        val o_SEq = Output(UInt(1.W))
        val o_SSup = Output(UInt(1.W))
        val o_opM = Output(UInt(3.W))
        val o_opE = Output(UInt(3.W))
        val o_cd = Output(UInt(1.W))
        val o_adr_des = Output(UInt(5.W))
        val o_writeEnable = Output(Bool())
    })

    /* Wire */
    val wire_s1 = Wire(UInt(1.W))
    wire_s1 := io.i_f1(31)

    val wire_s2 = Wire(UInt(1.W))
    wire_s2 := io.i_f2(31)

    val wire_writeEnable = Wire(Bool())
    wire_writeEnable := false.B

    val wire_M1 = Wire(UInt(23.W))
    wire_M1 := 0.U
    
    val wire_M2 = Wire(UInt(23.W))
    wire_M2 := 0.U

    val wire_E1 = Wire(UInt(8.W))
    wire_E1 := 0.U
    
    val wire_E2 = Wire(UInt(8.W))
    wire_E2 := 0.U

    val wire_S = Wire(UInt(1.W))
    wire_S := 0.U
    
    val wire_opM = Wire(UInt(3.W))
    wire_opM := 0.U
    
    val wire_opE = Wire(UInt(3.W))
    wire_opE := 0.U

    val wire_cd = Wire(UInt(1.W))
    wire_cd := 0.U

    /* Composant */

    val Comparateur = Module(new Comparator(1))
    Comparateur.io.i_inf := 0.U
    Comparateur.io.i_eq := 1.U
    Comparateur.io.i_sup := 0.U
    Comparateur.io.i_scr1 := wire_s1
    Comparateur.io.i_scr2 := wire_s2

    /* Commandes */
    switch(io.i_instruction){
        is(FPUUOP.ADD.U){
            when(wire_s1 === wire_s2){
                wire_M1 := io.i_f1(22,0)
                wire_M2 := io.i_f2(22,0)
                wire_E1 := io.i_f1(30,23)
                wire_E2 := io.i_f2(30,23)

                wire_opM := FPUUOP.ADD.U
                wire_opE := FPUUOP.SUB.U
                wire_cd := 1.U
                wire_S := wire_s1
                wire_writeEnable := true.B
            }. elsewhen(wire_s1 < wire_s2){

                wire_M1 := io.i_f1(22,0)
                wire_M2 := io.i_f2(22,0)
                wire_E1 := io.i_f1(30,23)
                wire_E2 := io.i_f2(30,23)

                wire_opM := FPUUOP.SUB.U
                wire_opE := FPUUOP.SUB.U
                wire_cd := 1.U
                wire_S := 0.U
                wire_writeEnable := true.B
            }. otherwise{

                wire_M1 := io.i_f2(22,0)
                wire_M2 := io.i_f1(22,0)
                wire_E1 := io.i_f2(30,23)
                wire_E2 := io.i_f1(30,23)

                wire_opM := FPUUOP.SUB.U
                wire_opE := FPUUOP.SUB.U
                wire_cd := 1.U
                wire_S := 0.U
                wire_writeEnable := true.B
            }
        }
        is(FPUUOP.SUB.U){
             when(wire_s1 === wire_s2){
                when(wire_s1 === 0.U){
                    wire_M1 := io.i_f1(22,0)
                    wire_M2 := io.i_f2(22,0)
                    wire_E1 := io.i_f1(30,23)
                    wire_E2 := io.i_f2(30,23)
                }. otherwise{
                    wire_M1 := io.i_f2(22,0)
                    wire_M2 := io.i_f1(22,0)
                    wire_E1 := io.i_f2(30,23)
                    wire_E2 := io.i_f1(30,23)
                }
                wire_opM := FPUUOP.SUB.U
                wire_opE := FPUUOP.SUB.U
                wire_cd := 1.U
                wire_S := 0.U
                wire_writeEnable := true.B
            }. elsewhen(wire_s1 < wire_s2){
                wire_M1 := io.i_f1(22,0)
                wire_M2 := io.i_f2(22,0)
                wire_E1 := io.i_f1(30,23)
                wire_E2 := io.i_f2(30,23)

                wire_opM := FPUUOP.ADD.U
                wire_opE := FPUUOP.SUB.U
                wire_cd := 1.U
                wire_S := 0.U
                wire_writeEnable := true.B
            }. otherwise{
                wire_M1 := io.i_f1(22,0)
                wire_M2 := io.i_f2(22,0)
                wire_E1 := io.i_f1(30,23)
                wire_E2 := io.i_f2(30,23)

                wire_opM := FPUUOP.ADD.U
                wire_opE := FPUUOP.SUB.U
                wire_cd := 1.U
                wire_S := 1.U
                wire_writeEnable := true.B
            }
        }
        is(FPUUOP.MAX.U){      
            wire_M1 := io.i_f1(22,0)
            wire_M2 := io.i_f2(22,0)
            wire_E1 := io.i_f1(30,23)
            wire_E2 := io.i_f2(30,23)

            wire_opM := FPUUOP.MAX.U
            wire_opE := FPUUOP.MAX.U
            wire_cd := 0.U
            wire_S := 0.U
            wire_writeEnable := true.B
        }
        is(FPUUOP.MIN.U){
            wire_M1 := io.i_f1(22,0)
            wire_M2 := io.i_f2(22,0)
            wire_E1 := io.i_f1(30,23)
            wire_E2 := io.i_f2(30,23)

            wire_opM := FPUUOP.MIN.U
            wire_opE := FPUUOP.MIN.U
            wire_cd := 0.U
            wire_S := 0.U
            wire_writeEnable := true.B
        }
    }

    io.o_adr_des := io.i_adr_des
    io.o_SInf := Comparateur.io.o_sup
    io.o_SEq := Comparateur.io.o_eq
    io.o_SSup := Comparateur.io.o_inf
    io.o_M1 := wire_M1
    io.o_M2 := wire_M2
    io.o_E1 := wire_E1
    io.o_E2 := wire_E2
    io.o_S := wire_S
    io.o_opM := wire_opM
    io.o_opE := wire_opE
    io.o_cd := wire_cd
    io.o_writeEnable := wire_writeEnable

    
}
