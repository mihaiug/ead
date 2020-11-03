package ro.ucv.inf.ead.filter;

import java.io.IOException;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation used to ban a list of specified IPs. To use
 * filter you must add in web.xml the following:
 * 
 * <filter>
 *		<filter-name>ClientIPBlockFilter</filter-name>
 *		<filter-class>ro.ucv.inf.ead.filter.ClientIPFilter</filter-class>
 *		<init-param>
 *			<param-name>bannedIPs</param-name>
 *			<param-value>0:0:0:0:0:0:0:1;193.231.40.148</param-value>
 *		</init-param>
 *	</filter>
 * 
 */
public class ClientIPFilter implements Filter {

	/**
	 * Keep list of banned ips.
	 */
	private Set<String> bannedIPs = new LinkedHashSet<>();

	/**
	 * Default constructor.
	 */
	public ClientIPFilter() {

	}

	/**
	 * Called by the web container to indicate to a filter that it is being placed
	 * into service. The servlet container calls the init method exactly once after
	 * instantiating the filter. The init method must complete successfully before
	 * the filter is asked to do any filtering work.
	 * 
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig config) throws ServletException {
		// Gets filter configuration parameter containing banned ips.
		String bannedIPsParam = config.getInitParameter("bannedIPs");
		if (bannedIPsParam != null) {
			String ips[] = bannedIPsParam.split(";");
			for (String ip : ips) {
				bannedIPs.add(ip.trim());
			}
		}
		System.out.println("Filter " + this.getClass().getCanonicalName()
				+ " was initialized. List of IPs to be banned: " + bannedIPs);
	}

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		System.out.println("Filter " + this.getClass().getCanonicalName() + " was destroyed!");
	}

	/**
	 * Called by the container each time a request/response pair is passed through
	 * the chain due to a client request for a resource at the end of the chain.
	 * 
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (response instanceof HttpServletResponse) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			String requestURI = httpRequest.getRequestURI();
			String currentClientIP = request.getRemoteAddr();

			System.out.println("Processing request from [" + currentClientIP + "]. Requested " + requestURI);

			if (bannedIPs.contains(currentClientIP)) {
				System.out.println("\tRequest are not allowed!");
				httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN,
						"Your IP are not allowed to access the servlet!");
				return;
			} else {
				System.out.println("\tRequest are allowed!");
			}
		}
		
		// Pass the request along the filter chain.
		System.out.println("Before pass request to filter chain");
		chain.doFilter(request, response);
		System.out.println("After pass request to filter chain");
	}
}
