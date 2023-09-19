package combinational

import chisel3._
import circt.stage.ChiselStage

object Main extends App {
  // These lines generate the Verilog output
  ChiselStage.emitSystemVerilog(
    new Arbiter(4),
    firtoolOpts = Array("-disable-all-randomization", "-strip-debug-info", "--verilog", "-o", "generated", "--split-verilog")
  )
}
