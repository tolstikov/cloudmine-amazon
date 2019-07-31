package com.cloudaware.cloudmine.amazon.war;

import com.google.api.client.http.HttpStatusCodes;
import com.google.inject.Singleton;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Singleton
public class AuthFilter implements Filter {
    private String key;

    @Override
    public void init(final FilterConfig filterConfig) {
        final InputStream resourceAsStream = getClass().getResourceAsStream("/cloudmine-amazon.properties");
        if (resourceAsStream == null) {
            throw new IllegalStateException("Can't find cloudmine-amazon.properties");
        }
        final Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            throw new IllegalStateException("Can't load cloudmine-amazon.properties");
        }
        key = properties.getProperty("aws.apiKey");
    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
        //getParameter call with gzipped content throws exception
        final String apiKey = servletRequest.getParameter("key");
        if (apiKey != null && apiKey.equals(key)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.sendError(HttpStatusCodes.STATUS_CODE_UNAUTHORIZED, "Empty or invalid ApiKey");
    }

    @Override
    public void destroy() {
    }
}
