package fpu

import chisel3._
import chisel3.util._
import chisel3.iotesters
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class UnitTestR1 extends ChiselFlatSpec{
    behavior of "Comparator"

    //first unit test
    it should "test comparaison f1 > f2 and prev_eq = 1, prev_sup = prev_inf = 0" in {
        Driver.execute(Array("--generate-vcd-output", "on", "--target-dir", "output/", "--is-verbose"),
        () => new Comparator(3)) {
            c => new ComparatorTest0(c)
        } should be (true)
    }
}