package fpu

import chisel3._
import chisel3.util._

class Calculation extends Module{
    val io = IO( new Bundle{
        val i_opM = Input(UInt(3.W))
        val i_scr1 = Input(UInt(23.W))
        val i_scr2 = Input(UInt(23.W))
        val i_E1 = Input(UInt(8.W))
        val i_E2 = Input(UInt(8.W))
        val i_signe = Input(UInt(1.W))
        val i_sign_diff = Input(UInt(8.W))
        val i_Ne = Input(Bool())
        val i_adr_des = Input(UInt(5.W))
        val i_cd = Input(UInt(1.W))
        val i_inf = Input(UInt(1.W))
        val i_eq = Input(UInt(1.W))
        val i_sup = Input(UInt(1.W))
        val i_writeEnable = Input(Bool())

        val o_alu = Output(UInt(23.W))
        val o_signe = Output(UInt(1.W))
        val o_exp = Output(UInt(8.W))
        val o_adr_des = Output(UInt(5.W))
        val o_writeEnable = Output(Bool())

    })

    /* Composants */
    val Alu = Module(new Alu(23))

    /* Connections */
    Alu.io.i_op := io.i_opM
    Alu.io.i_scr1 := io.i_scr1
    Alu.io.i_scr2 := io.i_scr2
    Alu.io.i_inf := io.i_inf
    Alu.io.i_eq := io.i_eq
    Alu.io.i_sup := io.i_sup

    /* Programme */


    //choix exposant
    when(io.i_Ne){
        when(io.i_cd === 1.U){
            io.o_exp := io.i_E2
        }. otherwise{ io.o_exp := io.i_sign_diff}
    }. otherwise{
        when(io.i_cd === 1.U){
            io.o_exp := io.i_E1
        }. otherwise{ io.o_exp := io.i_sign_diff}
    }

    // Ajust signe
    io.o_signe := io.i_signe + Alu.io.o_N

    //Output
    io.o_adr_des := io.i_adr_des
    io.o_writeEnable := io.i_writeEnable


    
}