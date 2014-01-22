object FrameCommand extends Enumeration {
  type FrameCommand = Value
  val CONNECT, 
      CONNECTED, 
      SEND,
      SUBSCRIBE,
      UNSUBSCRIBE,
      BEGIN,
      COMMIT,
      ABORT,
      ACK,
      NACK,
      DISCONNECT = Value
}
