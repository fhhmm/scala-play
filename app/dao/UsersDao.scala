package dao

import scala.concurrent.Future

import javax.inject.Inject
import models.User
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import scala.concurrent.ExecutionContext
import slick.jdbc.JdbcProfile

class UsersDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._

  private val Users = TableQuery[UsersTable]

  def all()(implicit ec: ExecutionContext): Future[Seq[User]] = db.run(Users.result)

  def insert(user: User)(implicit ec: ExecutionContext): Future[Unit] = db.run(Users += user).map { _ => () }

  private class UsersTable(tag: Tag) extends Table[User](tag, "USERS") {

    def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
    def name = column[String]("NAME")
    def age = column[Int]("AGE")

    def * = (id.?, name, age) <> ((User.apply _).tupled, User.unapply)
  }
}
