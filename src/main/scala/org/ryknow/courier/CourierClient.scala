package org.ryknow.courier

import java.nio._
import java.nio.channels._
import java.net._

class CourierClient(host: String, port: Int) {
  val socketChannel: SocketChannel = new SocketChannel
  val socketAddress: SocketAddress = new InetSocketAddress(host, port)
  val byteBuffer: ByteBuffer = ByteBuffer.allocate(64 * 1024)
  val readBB: ByteBuffer = ByteBuffer.allocate(64 * 1024)

  def connect {
    val connectFrame: String = "CONNECT\naccept-version:1.0,1.1,1.2\nhost:" + host + "\n\n\0"
    val byteArray: Array[Byte] = connectFrame.toCharArray.map { c => c.asInstanceOf[Byte] }
    
    byteBuffer.put(byteArray)
    
    socketChannel.connect(socketAddress)
    socketChannel.write(byteBuffer)
    
    socketChannel.read(readBB)
  }
  
  def close {
    socketChannel.close
  }
}
