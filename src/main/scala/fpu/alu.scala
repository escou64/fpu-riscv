package fpu

import chisel3._
import chisel3.util._


class Alu(nBit: Int) extends Module{
    val io = IO(new Bundle{
        //I/O listing
        val i_op = Input(UInt(3.W))
        val i_scr1 = Input(UInt(nBit.W))
        val i_scr2 = Input(UInt(nBit.W))
        val i_inf = Input(UInt(1.W))
        val i_eq = Input(UInt(1.W))
        val i_sup = Input(UInt(1.W))

        val o_res = Output(UInt(nBit.W))
        val o_N = Output(UInt(1.W))        //1 si le resultat est négatif, O sinon
        val o_inf = Output(UInt(1.W))
        val o_eq = Output(UInt(1.W))
        val o_sup = Output(UInt(1.W))
    })

    //Composant
    val Comparateur = Module(new Comparator(nBit))


    Comparateur.io.i_scr1 := io.i_scr1
    Comparateur.io.i_scr2 := io.i_scr2
    Comparateur.io.i_inf := io.i_inf
    Comparateur.io.i_eq := io.i_eq
    Comparateur.io.i_sup := io.i_sup

    //New wire
    val wire_res = Wire(UInt(nBit.W))
    wire_res := 0.U

    val wire_N = Wire(UInt(1.W))
    wire_N := 0.U

    //Operation- Register connection
    

    switch(io.i_op){
        is(FPUUOP.ADD.U){
            wire_res := io.i_scr1 + io.i_scr2
            wire_N:= 0.U
        }
        is(FPUUOP.SUB.U){
            when(io.i_scr1 > io.i_scr2){
                wire_res := io.i_scr1 - io.i_scr2
                wire_N := 0.U
            } .otherwise{
                wire_res := io.i_scr2 - io.i_scr1
                wire_N:= 1.U
            }
        }
        is(FPUUOP.MIN.U){
           when((Comparateur.io.o_inf === 1.U) & (Comparateur.io.o_eq === 0.U) & (Comparateur.io.o_sup === 0.U)){
               wire_res := io.i_scr1
           }. elsewhen((Comparateur.io.o_inf === 0.U) & (Comparateur.io.o_eq === 0.U) & (Comparateur.io.o_sup === 1.U)){
               wire_res := io.i_scr2
           }. elsewhen((Comparateur.io.o_inf === 0.U) & (Comparateur.io.o_eq === 1.U) & (Comparateur.io.o_sup === 0.U)){
               wire_res := io.i_scr2
           }. otherwise{ wire_res := 0.U }
           wire_N:= 0.U
        }
        is(FPUUOP.MAX.U){
            when((Comparateur.io.o_inf === 1.U) & (Comparateur.io.o_eq === 0.U) & (Comparateur.io.o_sup === 0.U)){
               wire_res := io.i_scr2
           }. elsewhen((Comparateur.io.o_inf === 0.U) & (Comparateur.io.o_eq === 0.U) & (Comparateur.io.o_sup === 1.U)){
               wire_res := io.i_scr1
           }. elsewhen((Comparateur.io.o_inf === 0.U) & (Comparateur.io.o_eq === 1.U) & (Comparateur.io.o_sup === 0.U)){
               wire_res := io.i_scr1
           }. otherwise{ wire_res := 0.U }
           wire_N:= 0.U
        }
    }

    // Output connection
    io.o_res := wire_res
    io.o_inf := Comparateur.io.o_inf
    io.o_eq := Comparateur.io.o_eq
    io.o_sup := Comparateur.io.o_sup
    io.o_N := wire_N
}