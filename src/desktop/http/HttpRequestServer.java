package desktop.http;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
 
public class HttpRequestServer extends HttpServlet
{	
	 protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		        throws ServletException, IOException
		    {
		        String q = req.getParameter("op");
		        try {
		        	EventController ec = new EventController();
		        	ec.run(q);
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        PrintWriter out = resp.getWriter();

		        out.println("<html>");
		        out.println("<body>");
		        out.println("The paramter op was \"" + q + "\".");
		        out.println("</body>");
		        out.println("</html>");
		    }

		    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		        throws ServletException, IOException
		    {
		        String field = req.getParameter("op");
		        try {
		        	EventController ec = new EventController();
		        	ec.run(field);
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        PrintWriter out = resp.getWriter();

		        out.println("<html>");
		        out.println("<body>");
		        out.println("You entered \"" + field + "\" into the text box.");
		        out.println("</body>");
		        out.println("</html>");
		    }
    
    public static void main(String[] args) throws Exception
    {
        Server server = new Server(8080);
 
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
 
        context.addServlet(new ServletHolder(new HttpRequestServer()),"/*");
        //context.addServlet(new ServletHolder(new WebService("Buongiorno Mondo")),"/it/*");
        //context.addServlet(new ServletHolder(new WebService("Bonjour le Monde")),"/fr/*");
 
        server.start();
        server.join();
    }
}