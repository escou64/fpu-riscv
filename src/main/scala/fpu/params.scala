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



/* paramètres à l'étage de décalage 
trait ShiftParams{
    def nShiftS: Int
    def nShiftE: Int
}

case class SihftConfig(
    nShiftS: Int,
    nShiftE: Int

)extends ShiftParams

object ShiftDefault extends ShiftConfig(
    nShiftS = 1,
    nShiftE = 1
)*/
