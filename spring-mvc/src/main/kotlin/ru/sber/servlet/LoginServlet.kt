package ru.sber.servlet

import java.time.Instant
import javax.servlet.annotation.WebServlet
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "AuthServlet", urlPatterns = ["/login"])
class LoginServlet : HttpServlet() {
    private val login = "login"
    private val password = "password"

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
//        println("urlPatterns ${req!!.requestURI}")
//        resp?.addCookie(Cookie("auth", ""))
        req?.getRequestDispatcher("/login.html")?.forward(req, resp)
    }

    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        if (login == req!!.getParameter("login") && password == req.getParameter("password")) {
            resp?.addCookie(Cookie("auth", Instant.now().toString()))
            resp?.sendRedirect("/app")
        } else {
            resp?.sendRedirect("/authError.html")
        }
    }
}
