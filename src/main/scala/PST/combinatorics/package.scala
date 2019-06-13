package PST

import scala.language.implicitConversions
import scala.util.Random

package object combinatorics {
  def factorial(n: Int): BigInt = {
    def factorialAcc(acc: BigInt, n: BigInt): BigInt = {
      if (n <= 1) acc
      else factorialAcc(n * acc, n - 1)
    }
    factorialAcc(1, n)
  }

  def combination(n: Int, k: Int): BigInt = factorial(n) / (factorial(k) * factorial(n - k))

  //  type Predicate[T] = T => Boolean

  class ChooseClause(k: Int) {
    def from(n: Int): Combination = new Combination(n, k)
  }
  class ArrayChooseClause[T](predicate: T => Boolean) {
    def from(seq: Seq[T]): Combination = new Combination(seq.length, seq.count(predicate))
  }


  //    def from(count: Int): Int = combination(seq.length, seq.count(predicate))

  class Combination(n: Int, k: Int) {
    implicit def toInt: BigInt = combination(n, k)
    override implicit def toString: String = toInt.toString
    def sorted: BigInt = toInt * factorial(k)
  }

  implicit def toInt(combination: Combination): BigInt = combination.toInt

  def choose(k: Int): ChooseClause = {
    new ChooseClause(k)
  }

  def choose[T](predicate: T => Boolean): ArrayChooseClause[T] = {
    new ArrayChooseClause[T](predicate)
  }

  def switch(count: Int): BigInt = factorial(count)


}
