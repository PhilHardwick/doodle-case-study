//package doodle.core
//
///**
//  * Bounding box with x and y as it's centre coords and width and height of the entire
//  * box i.e. the edges are half the height and width away from the centre
//  */
//case class BoundingBox(originX: Double, originY: Double, w: Double, h: Double) {
//
//  def getCentre: (Double, Double) = {
//    (originX, originY)
//  }
//
//  /**
//    * Returns the Bounding box of both bounding boxes
//    * @return
//    */
//  def on(that: BoundingBox): BoundingBox = {
//    BoundingBox(originX, originY, this.w max that.w, this.h max that.h)
//  }
//
//  def beside(that: BoundingBox): BoundingBox = {
//    BoundingBox(((this.originX max that.originX - this.originX min that.originX)/2) + (this.originX min that.originX),
//      originY, this.w + that.w, this.h max that.h)
//  }
//
//  def above(that: BoundingBox): BoundingBox = {
//    BoundingBox(originX, ((this.originY max that.originY - this.originY min that.originY)/2) + (this.originY min that.originY),
//      this.w + that.w, this.h max that.h)
//  }
//
//}

package doodle
package core

final case class BoundingBox(left: Double, top: Double, right: Double, bottom: Double) {
  def move(transformation: Vec): BoundingBox = {
    BoundingBox(
      this.left + transformation.x,
      this.top + transformation.y,
      this.right + transformation.x,
      this.bottom + transformation.y
    )
  }

  val height: Double = top - bottom
  val width: Double = right - left

  def above(that: BoundingBox): BoundingBox =
    BoundingBox(
      this.left min that.left,
      (this.height + that.height)/2,
      this.right max that.right,
      -(this.height + that.height)/2
    )

  def beside(that: BoundingBox): BoundingBox =
    BoundingBox(
      -(this.width + that.width)/2,
      this.top max that.top,
      (this.width + that.width)/2,
      this.bottom min that.bottom
    )

  def on(that: BoundingBox): BoundingBox =
    BoundingBox(
      this.left min that.left,
      this.top max that.top,
      this.right max that.right,
      this.bottom min that.bottom
    )
}
