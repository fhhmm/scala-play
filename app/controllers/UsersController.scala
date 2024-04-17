package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.db._
import dao.UsersDao
import models.User
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms.text
import play.api.libs.concurrent.Execution.Implicits.defaultContext

@Singleton
class UsersController @Inject()(usersDao: UsersDao) extends Controller {

  def index = Action {
    usersDao.all().map {
        users => Ok(views.html.index(users))
    }
  }
  def create = Action.async { implicit request =>
    val user: User = userForm.bindFromRequest.get
    catDao.insert(cat).map(_ => Redirect(routes.HomeController.index))
  }

  val userForm = Form(
    mapping(
      "id" -> number,
      "name" -> nonEmptyText,
      "color" -> nonEmptyText
    )(User.apply)(User.unapply)
  )
}