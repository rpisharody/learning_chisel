package combinational

import chisel3._
import circt.stage.ChiselStage

object Main extends App {
  // These lines generate the Verilog output
  ChiselStage.emitSystemVerilog(
    new PriorityEncoder(16),
    firtoolOpts = Array("-disable-all-randomization", "-strip-debug-info", "--verilog", "-o", "generated", "--split-verilog")
  )
}
