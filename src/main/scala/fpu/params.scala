package fpu

import chisel3._
import chisel3.util._

trait  RegFileParams{
    def nRegFileRead: Int
    def nRegFileWrite: Int
}

case class RegFileConfig(
    nRegFileRead: Int,
    nRegFileWrite: Int
)extends RegFileParams

object RegFileDefault extends RegFileConfig(
    nRegFileRead = 2,
    nRegFileWrite = 1
)
