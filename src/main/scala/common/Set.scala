package common

import common.exceptions.DuplicateElementsException
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
case class Set[+A](private val elements: List[A]) {
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

  def this(set: Set[A]) = this(set.elements)

  def foreach(function: A => Any): Unit = elements.foreach(function)

  override def toString: String = {
    var arrayBuffer = ArrayBuffer[String]()
    foreach {
      case set: Set[Any] => if (set.size < 1) arrayBuffer += "∅" else arrayBuffer += set.toString
      case e => arrayBuffer += e.toString
    }
    arrayBuffer.mkString("{", ", ", "}")
  }

  def pr(): Unit = {
    println(toString)
  }

  /**
    * Sets A and B are equal if and only if they have precisely the same elements.
    *
    */
  override def equals(that: Any): Boolean = {
    that match {
      case that: Set[A] => that.isInstanceOf[Set[A]] && {
        if (size != that.size) return false
        elements.forall(e => that.elements.contains(e))
      }
      case that: A => size == 1 && elements.contains(that)
      case _ => false
    }
  }

  def size: Int = elements.length

  def ⊆[B >: A](that: Set[B]): Boolean = elements.forall(e => that.elements.contains(e))

  def ⋃[B >: A](that: Set[B]): Set[B] = new Set[B](that.elements.filter(e => !elements.contains(e)) ++ elements)

  def ⋂[B >: A](that: Set[B]): Set[B] = new Set[A](elements.filter(e => that.elements.contains(e)))

  def \[B >: A](that: Set[B]): Set[B] = new Set[A](elements.filter(e => !that.elements.contains(e)))

  def ∈:[B >: A](element: B): Boolean = elements.contains(element)

  def ×[B, C >: A](that: Set[B]): CartesianProduct[C, B] = new Set[(A, B)](for (e <- elements;
                                                                                e2 <- that.elements) yield (e, e2))
}

object Set {
  def empty[B: ClassTag]: Set[B] = ∅[B]

  def ∅[B: ClassTag] = new Set[B]()

  def ∅ = new Set()
}