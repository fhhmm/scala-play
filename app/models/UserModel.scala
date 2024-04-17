package models

final case class User(
    id: Option[Int],
    name: String,
    age: Int
)
