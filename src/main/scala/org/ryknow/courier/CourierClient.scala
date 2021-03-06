package org.ryknow.courier

import io.netty.bootstrap.Bootstrap
import io.netty.channel.{ChannelFuture, ChannelInitializer, EventLoopGroup, Channel}
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel
import java.net.InetSocketAddress
import io.netty.channel.socket.SocketChannel
import io.netty.util.CharsetUtil
import io.netty.handler.codec.string.StringDecoder
import io.netty.buffer.{ByteBuf, Unpooled}

class CourierClient(host: String, port: Int) {
  private val bootstrap: Bootstrap  = new Bootstrap
  private val group: EventLoopGroup = new NioEventLoopGroup

  /**
   * Constructor using the default TCP port
   *
   * @param host  The host of the Stomp broker being connected to
   * @return      A new StompFrame object
   */
  def this(host: String) = this(host, 61613)

  /**
   * Bootstraps Netty and connects to the Stomp broker
   */
  private def connect(handler: ChannelInboundHandlerAdapter ) = {
    val channel: Channel = try {
      bootstrap.group(group)
               .channel(classOf[NioSocketChannel])
               .remoteAddress(new InetSocketAddress(host, port))
               .handler(new ChannelInitializer[SocketChannel] {
                 override def initChannel(ch: SocketChannel) {
                   // TODO: Add StompFrameDecoder to the pipeline
                   ch.pipeline//.addLast(new StringDecoder(CharsetUtil.UTF_8))
                              .addLast(handler)
                 }
               })

      bootstrap.connect.sync.channel
    }
  }

  /**
   * Sends a DISCONNECT StompFrame to the Stomp broker
   * Closes the channel and shuts down the EventLoopGroup to close the connection to the server
   */
  private def disconnect(channel: Channel) {
    val byteBuf: ByteBuf = Unpooled.buffer
    byteBuf.writeBytes(new StompFrame("DISCONNECT", Map(), "").toByteArray)

    channel.writeAndFlush(byteBuf).sync

    channel.disconnect.sync
    group.shutdownGracefully
  }

  /**
   * Publish creates a StompFrame SEND object and writes it to the Channel
   *
   * @param body         Body of the message being sent to the Stomp broker
   * @param destination  Destination of the message (Queue or Topic)
   */
  def publish(body: String, destination: String) {
    val channel: Channel = connect(new PublishHandler)
    val stompFrame: StompFrame = new StompFrame("SEND",
      Map("destination" -> destination, "content-length" -> body.length.toString, "content-type" -> "text/plain"), body)
    val byteBuf: ByteBuf = Unpooled.buffer

    try {
      byteBuf.writeBytes(stompFrame.toByteArray)

      channel.writeAndFlush(byteBuf)
    } finally {
      disconnect(channel)
    }
  }

  /**
   * Alias for the publish method
   *
   * @param body         Body of the message being sent to the messaging broker
   * @param destination  Destination of the message (Queue or Topic)
   */
  def send(body: String, destination: String) {
    publish(body, destination)
  }

  /**
   * Subscribes to the specified destination and performs the function with the received message
   *
   * @param destination
   * @param fn
   */
  def subscribe(destination: String, fn: ((StompFrame) => Unit)) {
    val channel: Channel = connect(new SubscribeHandler)
    // TODO: Implementation details
  }
}
