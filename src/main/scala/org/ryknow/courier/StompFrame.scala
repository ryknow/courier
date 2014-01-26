package org.ryknow.courier

class StompFrame(command: String, headers: Map[String, Any], body: String) {
  private val NULL    = "\0"
  private val NEWLINE = "\n"

  // TODO: Add methods for modifying the StompFrame i.e. changing the command, headers, body

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

  def isValid: Boolean = {
    if (command == null || command == "") false
    else true
  }
}
