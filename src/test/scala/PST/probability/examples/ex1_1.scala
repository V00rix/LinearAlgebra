package PST.probability.examples

import org.scalatest.FlatSpec

import scala.util.Random

class ex1_1 extends FlatSpec {
  private def printArr[T](array: Array[T]): Unit = array.foreach(println)

  "Monty Hall Problem" should "work" in {
    val tries: Int = 1e6.toInt
    var wins: Int = 0
    var looses: Int = 0


    def change(option: Boolean): Unit = {
      if (option) looses += 1 // option was true, we switched to false
      else wins += 1 // switched to true
    }

    def keep(option: Boolean): Unit = {
      if (option) wins += 1 // option was true, and we sticked to it
      else looses += 1 // kept false
    }

    def printStats(): Unit ={
      println(s"Won $wins out of $tries")
      println(s"Lost $looses out of $tries")
      println(s"Win percentage: ${wins.toDouble / tries.toDouble}")
    }

    def keepTest(): Unit = {
      println("Keep test")

      wins = 0
      looses = 0

      for (_ <- 1 to tries) {
        val selected = Random.shuffle(List(true, false, false)).head
        keep(selected)
      }
    }

    def changeTest(): Unit = {
      println("Change test")

      wins = 0
      looses = 0

      for (_ <- 1 to tries) {
        val selected = Random.shuffle(List(true, false, false)).head
        change(selected)
      }
    }

    changeTest()
    printStats()

    keepTest()
    printStats()
  }

  /**
    * Cvičení 1.1 (Monty Hall Problem). Hráč má uhádnout, za kterými z trojích dveří se skrývá výhra. Řekne
    * svůj tip, poté mu moderátor (který ví, kde výhra je) otevře jiné dveře, za kterými výhra není. Poté dá
    * hráči možnost změnit svůj tip. Má to hráč udělat?
    */
  "player" should "choose the highest probability of win" in {
    //    val hasGuessed = probability.in(true, false, false)
//    val field =
    // then opens other door
    // always opens "false" door, thus, we have
    //    val newProbability = probability.in(true, false)

    // old probability thus, though, becomes 2/3
    //    printArr(oldProbability)


    //    println(probability.in(oldProbability:_*))

    // Simulation


  }
}
