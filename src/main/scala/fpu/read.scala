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

    val wire_s1 = Wire(UInt(1.W))
    wire_s1 := io.i_f1(32)

    val wire_s2 = Wire(UInt(1.W))
    wire_s2 := io.i_f2(32)

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
                io.o_M1 := io.i_f1(0,22)
                io.o_M2 := io.i_f2(0,22)
                io.o_E1 := io.i_f1(23,30)
                io.o_E2 := io.i_f2(23,30)

                io.o_opM := FPUUOP.ADD.U
                io.o_opE := FPUUOP.SUB.U
                io.o_cd := 0.U
                io.o_S := wire_s1
                io.o_writeEnable := true.B
            }. elsewhen(wire_s1 < wire_s2){

                io.o_M1 := io.i_f1(0,22)
                io.o_M2 := io.i_f2(0,22)
                io.o_E1 := io.i_f1(23,30)
                io.o_E2 := io.i_f2(23,30)

                io.o_opM := FPUUOP.SUB.U
                io.o_opE := FPUUOP.SUB.U
                io.o_cd := 0.U
                io.o_S := 0.U
                io.o_writeEnable := true.B
            }. otherwise{

                io.o_M1 := io.i_f2(0,22)
                io.o_M2 := io.i_f1(0,22)
                io.o_E1 := io.i_f2(23,30)
                io.o_E2 := io.i_f1(23,30)

                io.o_opM := FPUUOP.SUB.U
                io.o_opE := FPUUOP.SUB.U
                io.o_cd := 0.U
                io.o_S := 0.U
                io.o_writeEnable := true.B
            }
        }
        is(FPUUOP.SUB.U){
             when(wire_s1 === wire_s2){
                when(wire_s1 === 0.U){
                    io.o_M1 := io.i_f1(0,22)
                    io.o_M2 := io.i_f2(0,22)
                    io.o_E1 := io.i_f1(23,30)
                    io.o_E2 := io.i_f2(23,30)
                }. otherwise{
                    io.o_M1 := io.i_f2(0,22)
                    io.o_M2 := io.i_f1(0,22)
                    io.o_E1 := io.i_f2(23,30)
                    io.o_E2 := io.i_f1(23,30)
                }
                io.o_opM := FPUUOP.SUB.U
                io.o_opE := FPUUOP.SUB.U
                io.o_cd := 0.U
                io.o_S := 0.U
                io.o_writeEnable := true.B
            }. elsewhen(wire_s1 < wire_s2){
                io.o_M1 := io.i_f1(0,22)
                io.o_M2 := io.i_f2(0,22)
                io.o_E1 := io.i_f1(23,30)
                io.o_E2 := io.i_f2(23,30)

                io.o_opM := FPUUOP.ADD.U
                io.o_opE := FPUUOP.SUB.U
                io.o_cd := 0.U
                io.o_S := 0.U
                io.o_writeEnable := true.B
            }. otherwise{
                io.o_M1 := io.i_f1(0,22)
                io.o_M2 := io.i_f2(0,22)
                io.o_E1 := io.i_f1(23,30)
                io.o_E2 := io.i_f2(23,30)

                io.o_opM := FPUUOP.ADD.U
                io.o_opE := FPUUOP.SUB.U
                io.o_cd := 1.U
                io.o_S := 1.U
                io.o_writeEnable := true.B
            }
        }
        is(FPUUOP.MAX.U){             
            io.o_opM := FPUUOP.MAX.U
            io.o_opE := FPUUOP.MAX.U
            io.o_cd := 1.U
            io.o_S := 0.U
            io.o_writeEnable := true.B
        }
        is(FPUUOP.MIN.U){
            io.o_opM := FPUUOP.MIN.U
            io.o_opE := FPUUOP.MIN.U
            io.o_cd := 1.U
            io.o_S := 0.U
            io.o_writeEnable := true.B
        }
    }

    io.o_adr_des := io.i_adr_des
    io.o_SInf := Comparateur.io.o_inf
    io.o_SEq := Comparateur.io.o_eq
    io.o_SSup := Comparateur.io.o_sup


    
}
