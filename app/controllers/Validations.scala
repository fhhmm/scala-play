package controllers

import models.User

object UserValidation {
  def validate(input: User): Either[Seq[String], User] = {
    val errors: Seq[String] = Seq(
      if (!startWithUppercase(input.name)) Some("Name must start with uppercase.") else None,
      if (!lessThan256Chars(input.name)) Some("Name must be 256 characters or fewer.") else None
    ).flatten

    if (errors.isEmpty) Right(input)
    else Left(errors)
  }

  def startWithUppercase(name: String): Boolean = {
    println(name)
    println(name.headOption.exists(_.isUpper))
    name.headOption.exists(_.isUpper)
  }

  def lessThan256Chars(name: String): Boolean = {
    name.length <= 256
  }
}
