package controllers

trait ValidationStrategy[T] {
  def validate(input: T): Either[List[String], T]
}

class UserNameValidation extends ValidationStrategy[String] {
  override def validate(input: String): Either[List[String], String] = {
    val errors: List[String] = List.empty[String]
    if (!input.headOption.exists(_.isUpper)) "Name must start with uppercase." :: errors

    if (errors.isEmpty) Right(input)
    else Left(errors)
  }
}
