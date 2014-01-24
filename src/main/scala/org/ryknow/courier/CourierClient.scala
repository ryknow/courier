package org.ryknow.courier

import java.nio._
import java.nio.channels._
import java.nio.charset._
import java.net._
import org.fusesource.hawtbuf.AsciiBuffer

class CourierClient(host: String, port: Int) {
  val socketChannel: SocketChannel = new SocketChannel
  val socketAddress: SocketAddress = new InetSocketAddress(host, port)
  val readBB: ByteBuffer = ByteBuffer.allocate(2048)

  def connect {
    val connectFrame = new StompFrame(new AsciiBuffer("CONNECT"))
    connectFrame.addHeader(new AsciiBuffer("login", new AsciiBuffer("user"))
    connectFrame.addHeader(new AsciiBuffer("passcode", new AsciiBuffer("password"))
    val byteBuffer: ByteBuffer = connectFrame.toBuffer.toByteBuffer
    
    socketChannel.connect(socketAddress)
    socketChannel.write(byteBuffer)
    
    val readResult = socketChannel.read(readBB)
    if (readResult == -1) {
      socketChannel.close
    } else {
      readBB.flip
      val byteArray: Array[Byte] = new Array[Byte](readBB.remaining)
      readBB.get(byteArray)
      val response: String = new String(byteArray, Charset.forName("ASCII"))
      println(response)
  }
  
  def close {
    socketChannel.close
  }
}
