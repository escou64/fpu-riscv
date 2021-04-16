package fpu

import chisel3._
import chisel3.util._

class AluExposant extends Module{
    val io = IO( new Bundle{
        //I/O listing
        val i_op = Input(UInt(3.W))
        val i_scr1 = Input(UInt(8.W))
        val i_scr2 = Input(UInt(8.W))
        val i_inf = Bool()
        val i_eq = Bool()
        val i_sup = Bool()

        val o_res = Output(UInt(8.W))
        val o_N = Output(Bool())        //1 si le resultat est négatif, O sinon
        val o_inf = Bool()
        val o_eq = Bool()
        val o_sup = Bool()
    })

    //Composant
    val Comparateur = Module(new Comparator(8))


    Comparateur.io.i_scr1 := io.i_scr1
    Comparateur.io.i_scr2 := io.i_scr2
    Comparateur.io.i_inf := io.i_inf
    Comparateur.io.i_eq := io.i_eq
    Comparateur.io.i_sup := io.i_sup

    //New register
    val reg_res = Wire(UInt(8.W))
    reg_res := 0.U

    //Operation- Register connection
    switch(io.i_op){
        is(FPUUOP.ADD.U){
            reg_res := io.i_scr1 + io.i_scr2
        }
        is(FPUUOP.SUB.U){
            when(io.i_scr1 > io.i_scr2){
                reg_res := io.i_scr1 - io.i_scr2
                io.o_N := 0.U
            } .otherwise{
                reg_res := io.i_scr2 - io.i_scr1
                io.o_N := 1.U
            }
        }
        is(FPUUOP.MIN.U){
           when(((Comparateur.io.i_inf === true.B) | (Comparateur.io.i_eq === true.B)) & (Comparateur.io.i_sup === false.B)){
               reg_res := io.i_scr2
           }. otherwise{ reg_res := io.i_scr1 }
        }
        is(FPUUOP.MAX.U){
            when((Comparateur.io.i_inf === false.B) & ((Comparateur.io.i_eq === true.B)) | (Comparateur.io.i_sup === true.B)){
               reg_res := io.i_scr1
           }. otherwise{ reg_res := io.i_scr2 }
        }
    }


    
    // Output connection
    io.o_res := reg_res
}