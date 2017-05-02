import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "AddHeaderFilter", urlPatterns = {"/*"})


public class ResponseFilter implements Filter {

  private final static Logger log = Logger.getLogger(ResponseFilter.class.getName() );

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    if (response instanceof HttpServletResponse) {
      log.info("Adding headers");
      HttpServletResponse http = (HttpServletResponse) response;
      http.addHeader("Access-Control-Allow-Origin", "*");
      log.info("adding Origin");
      http.addHeader("Access-Control-Allow-Credentials", "true");
      log.info("adding Credentials");
      http.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
      log.info("adding methods");
    } 
    chain.doFilter(request, response);
}
   @Override
  public void destroy(){
      
  }
  @Override
  public void init(FilterConfig f){
      
  }
}