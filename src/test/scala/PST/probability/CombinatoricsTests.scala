package PST.probability

import org.scalatest.{FlatSpec, Matchers}
import PST.combinatorics._

import scala.language.postfixOps

class CombinatoricsTests extends FlatSpec with Matchers{
  "Combinatorics" should "work" in {
    val red = (s: String) => s == "red"

    println(choose(1) from 2)
    println(choose(1) from 2 sorted)
    println(choose(1) from 3)
    println(choose(1) from 3 sorted)

    println(choose(red) from Seq("red", "green", "blue"))
    println(choose((x: String) => x == "red" || x == "blue") from Seq("red", "green", "blue") sorted)
    println(choose((x: String) => x == "red" || x == "blue") from Seq("red", "green", "blue"))
  }

  (choose(6) from 49 toInt) shouldBe 13983816
}
