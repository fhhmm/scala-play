package dao

import scala.concurrent.Future

import javax.inject.Inject
import models.User
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import scala.concurrent.ExecutionContext
import slick.jdbc.JdbcProfile

class UsersDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
extends HasDatabaseConfigProvider[JdbcProfile] with Tables {
  import profile.api._

  def all()(implicit ec: ExecutionContext): Future[Seq[User]] = db.run(Users.result)

  def insert(u: User)(implicit ec: ExecutionContext): Future[Unit] = db.run(Users += u).map { _ => () }
}
