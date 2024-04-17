package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.db._
import dao.UsersDao
import models.User
import play.api.data.Form
import play.api.data.Forms._
import scala.concurrent.ExecutionContext

@Singleton
class UsersController @Inject()(usersDao: UsersDao, cc: ControllerComponents)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def index = Action.async {
    usersDao.all().map {
        users => Ok(views.html.index(users))
    }
  }
  def create = Action.async { implicit request =>
    val user: User = userForm.bindFromRequest.get
    usersDao.insert(user).map(_ => Redirect(routes.UsersController.index))
  }

  val userForm = Form(
    mapping(
      "id" -> number,
      "name" -> nonEmptyText,
      "color" -> number
    )(User.apply)(User.unapply)
  )
}