package combinational

import chisel3._
import chisel3.util.{log2Ceil, switch, is}
import math.pow

class Decoder(input_bit_width:Int = 2) extends Module {
  val inb = input_bit_width
  val outb = pow(2, inb).toInt
  val io = IO(new Bundle {
    val in = Input(UInt(inb.W))
    val out = Output(UInt(outb.W))
  })
  io.out := 1.U << io.in
}

class Four_Bit_Encoder extends Module {
  // Mostly to show the 'switch' statement usage
  // Use 'Encoder' for a Generic Implementation
  val io = IO(new Bundle {
    val in = Input(UInt(4.W))
    val out = Output(UInt(2.W))
  })

  val result = WireDefault(0.U(2.W))
  switch(io.in) {
    is("b0001".U) { result := "b00".U }
    is("b0010".U) { result := "b01".U }
    is("b0100".U) { result := "b10".U }
    is("b1000".U) { result := "b11".U }
  }
  io.out := result
}

class Encoder(input_bit_width:Int = 16) extends Module {
  val inb = input_bit_width
  val outb = log2Ceil(inb)
  val io = IO(new Bundle {
    val in = Input(UInt(inb.W))
    val out = Output(UInt(outb.W))
  })

  val enc_out = Wire(Vec(inb, UInt(outb.W)))
  enc_out(0) := 0.U
  for (i <- 1 until inb) {
    enc_out(i) := Mux(io.in(i), i.U, 0.U) | enc_out(i - 1)
  }
  io.out := enc_out(inb - 1)
}

class Arbiter(n_bits:Int = 4) extends Module {
  val io = IO(new Bundle {
    val req = Input(UInt(n_bits.W))
    val grant = Output(UInt(n_bits.W))
  })

  val req = VecInit(io.req.asBools)
  val grant = VecInit.fill(n_bits)(false.B)
  val notGranted = VecInit.fill(n_bits)(false.B)

  grant(0) := req(0)
  notGranted(0) := !grant(0)
  for(i <- 1 until n_bits) {
    grant(i) := req(i) && notGranted(i - 1)
    notGranted(i) := !req(i) && notGranted(i - 1)
  }

  io.grant := grant.asUInt
}
