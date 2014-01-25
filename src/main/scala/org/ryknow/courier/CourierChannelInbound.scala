package org.ryknow.courier

import io.netty.channel.{ChannelHandlerContext, ChannelInboundHandlerAdapter}

class CourierChannelInbound extends ChannelInboundHandlerAdapter {
  override def channelActive(ctx: ChannelHandlerContext) {
    // TODO: Send CONNECT frame when the channel becomes active
  }

  override def channelRead(ctx: ChannelHandlerContext, msg: Any) {
    // TODO: Verify that the type of frame received
  }

  override def exceptionCaught(ctx: ChannelHandlerContext, caught: Throwable) {
    // TODO: Log an error
    caught.printStackTrace
  }
}
