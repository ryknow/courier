package org.ryknow.courier

import org.scalatest.{BeforeAndAfter, FunSpec}

class StompFrameSpec extends FunSpec with BeforeAndAfter {
  var stompFrame: StompFrame = _

  before {
    stompFrame = new StompFrame("CONNECT", Map(), "")
  }

  describe("#toByteArray") {
    describe("valid frame") {
      it("returns an array of bytes") {
        assert(stompFrame.toByteArray === "CONNECT".getBytes)
      }
    }
  }

  describe("#isValid") {
    describe("without a command") {
      it("returns false") {
        val sf: StompFrame = new StompFrame(null, Map(), "")
        assert(sf.isValid === false)
      }
    }
    describe("with a command") {
      it("returns true") {
        assert(stompFrame.isValid === true)
      }
    }
  }
}
