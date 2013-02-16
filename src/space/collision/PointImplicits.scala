package space.collision


import vector.{Point2d, Vector2d}


object PointImplicits {

	import PointImplicits._
  	
	implicit def Vector2d2Point(value : Vector2d):Point = {
	  new Point(value.x,value.y)
	}
	
	implicit def Point2d2Point(value : Point2d):Point = {
	 value.getVector()
	}
	
	implicit def Tuple2Point(value: Tuple2[Double, Double]) = {
	  val (x,y) = value;
	  new Point(x, y)
	}

}