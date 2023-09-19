package combinational

import chisel3._
import chiseltest._
import chisel3.util._
import org.scalatest.freespec.AnyFreeSpec
import math.pow

class DecoderTests extends AnyFreeSpec with ChiselScalatestTester {
  // Testing multiple Decoders !
  for (bb <- 1 until 5) {
    s"$bb-Bit Decoder" in {
      test(new Decoder(bb)) { c =>
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

class FourBitEncoderTests extends AnyFreeSpec with ChiselScalatestTester {
  "Non-Generic, 4-Bit Encoder" in {
    test(new Four_Bit_Encoder()) { c =>
      for(in <- 0 until 4) {
        val in_val = 1 << in
        c.io.in.poke(in_val.U)
        c.io.out.expect(in.U)
      }
    }
  }
}

class EncoderTests extends AnyFreeSpec with ChiselScalatestTester {
  // Testing multiple Encoders
  for (bits <- 1 to 4) {
    val enc_bits = pow(2, bits).toInt
    s"$enc_bits-Bit Encoder" in {
      test(new Encoder(enc_bits)) { c =>
        for (in <- 0 until enc_bits) {
          val in_val = 1 << in
          c.io.in.poke(in_val.U)
          c.io.out.expect(in.U)
        }
      }
    }
  }
}
