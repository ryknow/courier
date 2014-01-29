package org.ryknow.courier

class StompFrame(val command: String, val headers: Map[String, String], val body: String) {
  private val NULL    = "\0"
  private val NEWLINE = "\n"

  def content: Array[Byte] = body.getBytes

  /**
   * Turns the StompFrame object into an array of bytes including newline and null characters
   * so that it is a valid frame to be sent to the Stomp broker
   *
   * @return  The current stomp frame as an array of bytes
   */
  def toByteArray: Array[Byte] = {
    if (isValid) {
      val sb: StringBuilder = new StringBuilder
      sb.append(command)
        .append(NEWLINE)

      headers.foreach(header => {
        sb.append(header._1)
          .append(":")
          .append(header._2)
          .append(NEWLINE)
      })

      sb.append(NEWLINE)
        .append(body)
        .append(NULL)

      sb.toString.getBytes
    } else {
      NULL.getBytes
    }
  }

  /**
   * Verifies that the current StompFrame has a command
   *
   * @return  Whether the StompFrame has a command present or not
   */
  def isValid: Boolean = {
    if (command == null || command == "") false
    else true
  }
}
