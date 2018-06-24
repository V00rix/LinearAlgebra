package common.sets

import common.exceptions.{DuplicateElementsException, OutOfDomainException}
import common.sets.Set.∅
import types.CartesianProduct

import scala.collection.mutable.ArrayBuffer
import scala.reflect.ClassTag

/**
  * A set is a well-defined collection of distinct objects.
  * To define (create) a set means to define that collection of objects.
  *
  * @param elements Collection of distinct objects
  * @tparam A Type of objects
  */
class Set[+A](protected[sets] val elements: List[A]) {
  /**
    * Sets should not contain duplicates
    */
  {
    var arrayBuffer = ArrayBuffer[Any]()

    foreach(e => arrayBuffer match {
      case x if x.contains(e) => throw DuplicateElementsException()
      case _ => arrayBuffer += e
    })
  }

  def this(elements: A*) = this(elements.toList)

  def foreach(function: A => Any): Unit = elements.foreach(function)

  override def toString: String = {
    var arrayBuffer = ArrayBuffer[String]()
    foreach {
      case set: Set[Any] => arrayBuffer += set.toString
      case e => arrayBuffer += e.toString
    }
    if (arrayBuffer.size < 1) "∅" else arrayBuffer.mkString("{", ", ", "}")
  }

  def print(): Unit = {
    println(toString)
  }

  /**
    * Sets A and B are equal if and only if they have precisely the same elements.
    *
    */
  override def equals(that: Any): Boolean = that match {
    case that: Set[A] => that.isInstanceOf[Set[A]] && {
      if (cardinality != that.cardinality) return false
      elements.forall(e => that.elements.contains(e))
    }
    case _ => false
  }

  def cardinality: Int = elements.length

  def ⊆[B >: A](that: Set[B]): Boolean = elements.forall(e => that.elements.contains(e))

  def \[B >: A](that: Set[B]): Set[B] = new Set(elements.filter(e => !that.elements.contains(e)))

  def \[B >: A](that: B): Set[A] = new Set[A](elements.filter(e => e != that))

  def ⋂[B >: A](that: Set[B]): Set[B] = new Set(elements.filter(e => that.elements.contains(e)))

  def ∆[B >: A](that: Set[B]): Set[B] = Set.from(((this \ that) ⋃ (that \ this)))

  def ⋃[B >: A](that: Set[B]): Set[B] = new Set(that.elements.filter(e => !elements.contains(e)) ++ elements)

  def ∈:[B >: A](element: B): Boolean = elements.contains(element)

  def ×[B, C >: A](that: Set[B]): CartesianProduct[C, B] = new Set(for (e <- elements;
                                                                        e2 <- that.elements) yield (e, e2))

  def ^(power: Int): Set[Any] = power match {
    case p if p == 0 => new Set(∅)
    case p if p == 1 => this
    case p if p == 2 => this × this
    case p if p > 2 => this ^ (p - 1)
    case _ => throw OutOfDomainException()
  }
}

object Set {
  def empty[B: ClassTag]: Set[B] = ∅[B]

  def ∅[B: ClassTag] = new Set[B]()

  def from[B](that: Set[B]) = new Set[B](that.elements)

  def ⋃(sets: Set[_]*): Set[_] = sets.reduce((a, b) => a ⋃ b)

  def ⋂(sets: Set[_]*): Set[_] = sets.reduce((a, b) => a ⋂ b)

  def ∏(sets: Set[_]*): Set[_] = sets.reduce((a, b) => a × b)

  /**
    * Power set
    */
  def P[B](that: Set[B]): Set[Any] = {
    that match {
      case p if p == ∅ => that ⋃ new Set(∅)
      case _ => {
        var set = new Set[Any](that)
        that.foreach(e => set = set ⋃ P(that \ e))
        set
      }
    }
  }
}