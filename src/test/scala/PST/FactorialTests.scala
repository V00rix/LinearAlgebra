package PST

import PST.combinatorics.factorial
import org.scalatest.{FreeSpec, Matchers}

class FactorialTests extends FreeSpec with Matchers {
  "Factorial" - {
    "should work for small numbers" in {
      factorial(4) shouldBe 1 * 2 * 3 * 4
    }
    "should equal 1 for numbers less than 2" in {
      factorial(1) shouldBe 1
      factorial(0) shouldBe 1
      factorial(-1) shouldBe 1
    }
    "should compute for big numbers" in {
      factorial(100) shouldBe (1 to 100).map(BigInt(_)).product
      factorial(1000) shouldBe (1 to 1000).map(BigInt(_)).product
    }
  }
}
