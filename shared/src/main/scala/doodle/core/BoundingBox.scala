package doodle.core

/**
  * Created by Phil on 23/02/2016.
  */
//case class BoundingBox(x: Int, y: Int, w: Int, h: Int) {
//
//  def getCenter(): (Int, Int) = {
//    (x + (w/2), y + (h/2))
//  }
//
//  def createNewBoxOn(newWidth: Int, newHeight: Int): BoundingBox = {
//    BoundingBox(getCenter()._1 - (newWidth/2), getCenter()._2 - (newHeight/2), newWidth, newHeight)
//  }
//
//  def createNewBoxBeside(newWidth: Int, newHeight: Int): BoundingBox = {
//    BoundingBox(w, getCenter()._2 - (newHeight/2), newWidth, newHeight)
//  }
//
//  def createNewBoxAbove(newWidth: Int, newHeight: Int): BoundingBox = {
//    BoundingBox(getCenter()._1 - (newWidth/2), y - (newHeight), newWidth, newHeight)
//  }
//
//}
case class BoundingBox(left: Int, top: Int, right: Int, bottom: Int) {

  def getCenter(): (Int, Int) = {

  }



}
