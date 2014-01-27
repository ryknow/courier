package org.ryknow.courier

import io.netty.channel.{ChannelHandlerContext, ChannelInboundHandlerAdapter}
import io.netty.buffer.Unpooled

class CourierChannelInbound extends ChannelInboundHandlerAdapter {
  override def channelActive(ctx: ChannelHandlerContext) {
    val connectFrame = new StompFrame("CONNECT", Map("login" -> "admin", "passcode" -> "password"), "")
    val byteBuf      = Unpooled.buffer

    byteBuf.writeBytes(connectFrame.toByteArray)
    ctx.writeAndFlush(byteBuf)
  }

  override def channelRead(ctx: ChannelHandlerContext, msg: Any) {
    // TODO: Verify that the type of frame received
    // For some reason seems to get stuck in here and not returning outa
    println(msg.asInstanceOf[String])
  }

  override def exceptionCaught(ctx: ChannelHandlerContext, caught: Throwable) {
    // TODO: Log an error
    caught.printStackTrace
  }
}