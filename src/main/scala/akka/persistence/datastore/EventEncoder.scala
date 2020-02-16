package akka.persistence.datastore

trait EventEncoder[T] {


  def cast(data: Any): T = data.asInstanceOf[T]

  
  protected def serialize: PartialFunction[T, String]

  /**
    * A partial function that serializes an event to a json representation
    * @return the json representation
    */
  def castAndSerialize(data: Any): String = serialize(cast(data))


  /**
    * A partial function that deserializes an event from some json representation
    * @return the event
    */
  def deserialize: PartialFunction[(String), T]

}

object NoneJsonEncoder extends EventEncoder[Any] {

  override def serialize: PartialFunction[Any,String] = PartialFunction.empty[Any, String]

  override def deserialize: PartialFunction[(String), Any] =
    PartialFunction.empty[(String), Any]
}
