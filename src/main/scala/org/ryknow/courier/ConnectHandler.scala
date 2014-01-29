package org.ryknow.courier

import io.netty.channel.{ChannelHandlerContext, ChannelInboundHandlerAdapter}
import io.netty.buffer.Unpooled

class ConnectHandler extends ChannelInboundHandlerAdapter {
  override def channelActive(ctx: ChannelHandlerContext) {
    val connectFrame = new StompFrame("CONNECT", Map("login" -> "admin", "passcode" -> "password"), "")
    val byteBuf = Unpooled.buffer

    byteBuf.writeBytes(connectFrame.toByteArray)
    ctx.writeAndFlush(byteBuf)
  }
}
