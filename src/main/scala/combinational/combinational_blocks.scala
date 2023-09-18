package combinational

import chisel3._
import chisel3.util._
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
