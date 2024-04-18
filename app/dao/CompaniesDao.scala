package dao

import scala.concurrent.Future

import javax.inject.Inject
import models.Company
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import scala.concurrent.ExecutionContext
import slick.jdbc.JdbcProfile

class CompaniesDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
extends HasDatabaseConfigProvider[JdbcProfile] with Tables {
  import profile.api._

  def all()(implicit ec: ExecutionContext): Future[Seq[Company]] = db.run(Companies.result)

  def insert(c: Company)(implicit ec: ExecutionContext): Future[Unit] = db.run(Companies += c).map { _ => () }
}
