package combinational

import chisel3._
import chiseltest._
import org.scalatest.freespec.AnyFreeSpec
import math.pow

class CombinationalTests extends AnyFreeSpec with ChiselScalatestTester {
  // Testing multiple Encoders !
  for (bb <- 1 until 5) {
    s"$bb-Bit Encoder" in {
      test(new Encoder(bb)) { c =>
        val lim = pow(2, bb).toInt
        for(in <- 0 until lim) {
          val result = 1 << in
          c.io.in.poke(in.U)
          c.io.out.expect(result.U)
        }
      }
    }
  }
}
