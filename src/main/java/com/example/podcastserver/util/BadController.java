package com.example.podcastserver.util;

import com.example.podcastserver.payload.Response;
import com.example.podcastserver.payload.Status;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Random;

@RestController
public class BadController implements ErrorController {

    @RequestMapping(value = "/error", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH}, produces = "application/json")
    public HttpEntity<Response> handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return ResponseEntity.ok(new Response(new Status(HttpStatus.NOT_FOUND), List.of()));
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return ResponseEntity.ok(new Response(new Status(HttpStatus.INTERNAL_SERVER_ERROR), List.of()));
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(new Response(new Status(HttpStatus.FORBIDDEN), List.of()));
            } else {
                return ResponseEntity.ok(new Response(new Status(statusCode), List.of()));
            }
        }
        return ResponseEntity.ok(new Response(new Status(400, "error"), List.of()));
    }

    @RequestMapping(value = "/error", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH}, produces = MediaType.TEXT_HTML_VALUE)
    public String handleError2(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return PageContent.error404.get(new Random().nextInt(PageContent.error404.size()));
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return PageContent.error500.get(new Random().nextInt(PageContent.error500.size()));
            } else {
                return PageContent.errorAll.get(new Random().nextInt(PageContent.errorAll.size())).replace("$statusCode$", status.toString()).replace("$statusMessage$", HttpStatus.valueOf(statusCode).getReasonPhrase());
            }
        }
        return PageContent.errorAll.get(new Random().nextInt(PageContent.errorAll.size())).replace("$statusCode$", "500").replace("$statusMessage$", HttpStatus.valueOf(500).getReasonPhrase());
    }

    @GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {}
}
