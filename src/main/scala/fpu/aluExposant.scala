package fpu

import chisel3._
import chisel3.util._

class AluExposant extends Module{
    val io = IO( new Bundle{
        //I/O listing
        val i_op = Input(UInt(3.W))
        val i_scr1 = Input(UInt(8.W))
        val i_scr2 = Input(UInt(8.W))
        val i_inf = Input(UInt(1.W))
        val i_eq = Input(UInt(1.W))
        val i_sup = Input(UInt(1.W))

        val o_res = Output(UInt(8.W))
        val o_N = Output(Bool())        //1 si le resultat est négatif, O sinon
        val o_inf = Output(UInt(1.W))
        val o_eq = Output(UInt(1.W))
        val o_sup = Output(UInt(1.W))
    })

    //Composant
    val Comparateur = Module(new Comparator(8))


    Comparateur.io.i_scr1 := io.i_scr1
    Comparateur.io.i_scr2 := io.i_scr2
    Comparateur.io.i_inf := io.i_inf
    Comparateur.io.i_eq := io.i_eq
    Comparateur.io.i_sup := io.i_sup

    //New register
    val wire_res = Wire(UInt(8.W))
    wire_res := 0.U

    //Operation- Register connection
    switch(io.i_op){
        is(FPUUOP.ADD.U){
            wire_res := io.i_scr1 + io.i_scr2
        }
        is(FPUUOP.SUB.U){
            when(io.i_scr1 > io.i_scr2){
                wire_res := io.i_scr1 - io.i_scr2
                io.o_N := 0.U
            } .otherwise{
                wire_res := io.i_scr2 - io.i_scr1
                io.o_N := 1.U
            }
        }
        is(FPUUOP.MIN.U){
           when(((Comparateur.io.i_inf === 1.U) | (Comparateur.io.i_eq === 1.U)) & (Comparateur.io.i_sup === 0.U)){
               wire_res := io.i_scr2
           }. otherwise{ wire_res := io.i_scr1 }
        }
        is(FPUUOP.MAX.U){
            when((Comparateur.io.i_inf === 0.U) & ((Comparateur.io.i_eq === 1.U)) | (Comparateur.io.i_sup === 1.U)){
               wire_res := io.i_scr1
           }. otherwise{ wire_res := io.i_scr2 }
        }
    }


    
    // Output connection
    io.o_res := wire_res
}