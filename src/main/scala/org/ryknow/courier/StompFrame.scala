package org.ryknow.courier

class StompFrame(command: String, headers: Map[String, Any], body: String) {
  private val NULL    = "\0"
  private val NEWLINE = "\n"

  // TODO: Add methods for modifying the StompFrame i.e. changing the command, headers, body

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
