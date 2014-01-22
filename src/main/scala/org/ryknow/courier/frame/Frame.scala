package main.scala.org.ryknow.courier.frame

trait Frame {
  val command: String
  val headers: Map[String, String]
  val body: String
}
