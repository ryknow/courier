package main.scala.org.ryknow.courier.frame

object ConnectFrame extends Frame {
  val command: String = "CONNECT"
  val headers: Map[String, String] = Map("accept-version" -> "1.0,1.1,1.2")
  val body: String = "^@"

  def apply(host: String) = {
    val headerString: String = (headers ++ Map("host" -> host)).foldLeft("") {(a, kv) => a + kv._1 + ": " + kv._2 + "\n"}
    s"$command\n$headerString\n$body"
  }
}
