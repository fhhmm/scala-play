package models

final case class Company(
    id: Option[Int],
    name: String,
    address: String
)
