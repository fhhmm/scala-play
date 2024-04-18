package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.db._
import dao.CompaniesDao
import models.Company
import play.api.data.Form
import play.api.data.Forms._
import scala.concurrent.ExecutionContext

@Singleton
class CompaniesController @Inject()(dao: CompaniesDao, cc: ControllerComponents)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def index = Action.async { implicit request =>
    dao.all().map {
        companies => Ok(views.html.companies(companies))
    }
  }

  def create = Action.async { implicit request =>
    val company: Company = companyForm.bindFromRequest.get
    dao.insert(company).map(_ => Redirect(routes.CompaniesController.index))
  }

  val companyForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "address" -> nonEmptyText
    )((name, address) => Company(None, name, address))
    (company => Some((company.name, company.address)))
  )
}
