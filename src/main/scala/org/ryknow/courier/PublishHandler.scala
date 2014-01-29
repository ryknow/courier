package org.ryknow.courier

import io.netty.channel.{ChannelHandlerContext, ChannelInboundHandlerAdapter}
import io.netty.buffer.Unpooled

class PublishHandler() extends ConnectHandler {
  override def channelRead(ctx: ChannelHandlerContext, msg: Any) {
    // TODO: Instead of turning into String and splitting to get the action just
    // decode the msg as a StompFrame and do a msg.action
    if (msg.asInstanceOf[String].split("\n").toList.head == "CONNECTED") {
      // Perform follow on function
    } else if (msg.asInstanceOf[String].split("\n").toList.head == "RECEIPT") {
      // Verify that it is for the message sent
    }
  }

  override def exceptionCaught(ctx: ChannelHandlerContext, caught: Throwable) {
    // TODO: Log an error
    caught.printStackTrace
  }
}
