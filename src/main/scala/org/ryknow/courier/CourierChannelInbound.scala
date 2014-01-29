package org.ryknow.courier

import io.netty.channel.{ChannelHandlerContext, ChannelInboundHandlerAdapter}
import io.netty.buffer.Unpooled

class CourierChannelInbound() extends ChannelInboundHandlerAdapter {
  override def channelActive(ctx: ChannelHandlerContext) {
    val connectFrame = new StompFrame("CONNECT", Map("login" -> "admin", "passcode" -> "password"), "")
    val byteBuf      = Unpooled.buffer

    byteBuf.writeBytes(connectFrame.toByteArray)
    ctx.writeAndFlush(byteBuf)
  }

  override def channelRead(ctx: ChannelHandlerContext, msg: Any) {
    if (msg.asInstanceOf[String].split("\n").toList.head == "CONNECTED") {
      // Perform follow on function
    }
  }

  override def exceptionCaught(ctx: ChannelHandlerContext, caught: Throwable) {
    // TODO: Log an error
    caught.printStackTrace
  }
}