package com.clackjones.connectivitymap.rest

import com.clackjones.connectivitymap.service.QuerySignatureProviderComponent
import org.scalatra.{NotFound, Ok, ScalatraServlet}
import org.scalatra.scalate.ScalateSupport

// JSON-related libraries
import org.json4s.{DefaultFormats, Formats}

// JSON handling support from Scalatra
import org.scalatra.json._

trait QuerySignatureControllerComponent {
  this: QuerySignatureProviderComponent =>
  val querySignatureController = new QuerySignatureController

  class QuerySignatureController extends ScalatraServlet with ScalateSupport with JacksonJsonSupport {

    protected implicit lazy val jsonFormats: Formats = DefaultFormats

    /**
     * retrieve all query signatures
     */
    get("/") {
      contentType = formats("json")

      Ok(querySignatureProvider.findAll())
    }

    /**
     * retrieve a specific query signature by name
     */
    get("/id/:name") {
      contentType = formats("json")
      val sigName = params("name")

      querySignatureProvider.find(sigName) match {
        case Some(sig) => Ok(sig)
        case None => NotFound(s"Could not find signature with the name $sigName")
      }
    }
  }

}
