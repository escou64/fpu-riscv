;buildInfoPackage: chisel3, version: 3.3.1, scalaVersion: 2.12.11, sbtVersion: 1.3.10
circuit RegFile : 
  module RegFile : 
    input clock : Clock
    input reset : UInt<1>
    output io : {b_rport : {flip addr : UInt<5>, data : UInt<32>}[2], b_wport : {flip en : UInt<1>, flip addr : UInt<5>, flip data : UInt<32>}[1]}
    
    reg reg_regfile : UInt<32>[32], clock @[registerFile.scala 25:26]
    when io.b_wport[0].en : @[registerFile.scala 31:31]
      reg_regfile[io.b_wport[0].addr] <= io.b_wport[0].data @[registerFile.scala 32:45]
      skip @[registerFile.scala 31:31]
    io.b_rport[0].data <= reg_regfile[io.b_rport[0].addr] @[registerFile.scala 40:28]
    io.b_rport[1].data <= reg_regfile[io.b_rport[1].addr] @[registerFile.scala 40:28]
    
