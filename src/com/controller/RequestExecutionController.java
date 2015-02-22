package com.controller;

import com.service.RequestExecutionService;
import com.validation.exceptions.NotEnoughtUserBalance;
import com.validation.exceptions.RequestNotExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Роман on 18.02.2015.
 */
@Controller
public class RequestExecutionController {
    @Autowired
    private RequestExecutionService requestExecutionService;

    private static final long TIME_OUT_POLLING = 20000;

    @RequestMapping(value = "/startRequest", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> startRequest(Principal principal, @RequestParam(value = "id") long requestId) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            requestExecutionService.startRequest(principal.getName(), requestId);
        } catch (RequestNotExist e) {
            result.put("success", false);
            result.put("errror", "wrongRequest");
        } catch (NotEnoughtUserBalance e) {
            result.put("success", false);
            result.put("error", "wrongBalance");
        }
        result.put("success", true);
        return result;
    }


    @RequestMapping(value = "/getRequestNumber", method = RequestMethod.GET)
    @ResponseBody
    public DeferredResult<Long> getRequestNumber(Principal principal, @RequestParam(value = "id") final long requestId) {
        final DeferredResult<Long> deferredResult = new DeferredResult<>(TIME_OUT_POLLING, "timeOut");
        deferredResult.onCompletion(new Runnable() {
            @Override
            public void run() {
                requestExecutionService.removeGetNumberDeferredResult(requestId);
            }
        });
        requestExecutionService.setGetNumberDeferredResult(requestId, deferredResult);
        return deferredResult;
    }

    @RequestMapping(value = "/setRequestNumber", method = RequestMethod.GET)
    @ResponseBody
    public String setNumber(@RequestParam(value = "requestId") long requestId,
                            @RequestParam(value = "number") long number) {
        requestExecutionService.setRequestNumber(requestId, number);
        return "asd";
    }

    @RequestMapping(value = "/submitRequestNumber", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> submitNumber(Principal principal, @RequestParam(value = "id") long requestId,
                                            @RequestParam(value = "submit") boolean submit) {
        Map<String, Object> result = new LinkedHashMap<>();
        requestExecutionService.userSubmitRequestNumber(requestExecutionService.getExecutableRequest(requestId),
                submit);
        result.put("success", true);
        return result;
    }

    @RequestMapping(value = "/getRequestCode", method = RequestMethod.GET)
    @ResponseBody
    public DeferredResult<String> getRequestCode(Principal principal, @RequestParam(value = "id") final long requestId) {
        final DeferredResult<String> deferredResult = new DeferredResult<>(TIME_OUT_POLLING, "timeOut");
        deferredResult.onCompletion(new Runnable() {
            @Override
            public void run() {
                requestExecutionService.removeGetCodeDeferredResult(requestId);
            }
        });
        requestExecutionService.setGetCodeDeferredResult(requestId, deferredResult);
        return deferredResult;
    }

    @RequestMapping(value = "/setRequestCode", method = RequestMethod.GET)
    @ResponseBody
    public String setCode(@RequestParam(value = "requestId") long requestId,
                          @RequestParam(value = "code") String number) {
        requestExecutionService.setRequestCode(requestId, number);
        return "asd";
    }

}
