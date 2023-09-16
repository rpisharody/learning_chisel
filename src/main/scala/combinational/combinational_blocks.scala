package combinational

import chisel3._
import chisel3.util._
import math.pow

class Encoder(input_bit_width:Int = 2) extends Module {
  val inb = input_bit_width
  val outb = pow(2, inb).toInt
  val io = IO(new Bundle {
    val in = Input(UInt(inb.W))
    val out = Output(UInt(outb.W))
  })
  io.out := 1.U << io.in
}
