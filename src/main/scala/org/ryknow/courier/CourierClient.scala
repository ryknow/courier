package org.ryknow.courier

import io.netty.bootstrap.Bootstrap
import io.netty.channel.{ChannelFuture, ChannelInitializer, EventLoopGroup, Channel}
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel
import java.net.InetSocketAddress
import io.netty.channel.socket.SocketChannel
import io.netty.util.CharsetUtil
import io.netty.handler.codec.string.StringDecoder

class CourierClient(host: String, port: Int) {
  private val bootstrap: Bootstrap  = new Bootstrap
  private val group: EventLoopGroup = new NioEventLoopGroup

  def this(host: String) = this(host, 61613)

  def connect {
    try {
      bootstrap.group(group)
               .channel(classOf[NioSocketChannel])
               .remoteAddress(new InetSocketAddress(host, port))
               .handler(new ChannelInitializer[SocketChannel] {
                 override def initChannel(ch: SocketChannel) {
                   ch.pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8))
                              .addLast(new CourierChannelInbound)
                 }
               })

      val future: ChannelFuture = bootstrap.connect.sync

      future.channel.closeFuture.sync
    } finally {
      group.shutdownGracefully
    }
  }

  // TODO: Implement publish and subscribe
}