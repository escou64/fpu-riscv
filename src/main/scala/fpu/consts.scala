package fpu

import chisel3._
import chisel3.util._


object FPUUOP {
  def NBIT = 3
  def X = 0

  def ADD = 0
  def SUB = 1
  def MUL = 2
  def DIV = 3
  def MIN = 4
  def MAX = 5

  def MVXW = 6
  def MVWX = 7
}

object FPUROUND {
  def NBIT = 3

  def RNE = 0
  def RTZ = 1
  def RDN = 2
  def RUP = 3
  def RMM = 4

  def DYN = 7
}