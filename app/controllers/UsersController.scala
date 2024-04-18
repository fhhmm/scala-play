package controllers

import javax.inject._
// import play.api._
import play.api.mvc.AbstractController
import play.api.mvc.Action
import play.api.mvc.AnyContent
import play.api.mvc.ControllerComponents
import play.api.mvc.Request
// import play.api.db._
// import dao.UsersDao
// import models.User
// import play.api.data.Form
// import play.api.data.Forms._
// import scala.concurrent.ExecutionContext
import scala.concurrent.Future

@Singleton
class UsersController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index = Action { implicit request: Request[AnyContent] =>
    (Ok("OK!!"))
  }

  // def create = Action.async { implicit request =>
  //   val user: User = userForm.bindFromRequest.get
  //   usersDao.insert(user).map(_ => Redirect(routes.UsersController.index))
  // }

  // val userForm = Form(
  //   mapping(
  //     "name" -> nonEmptyText,
  //     "age" -> number,
  //     "companyId" -> optional(number)
  //   )((name, age, companyId) => User(None, name, age, companyId))
  //   (u => Some((u.name, u.age, u.companyId)))
  // )
}
