package de.conciso.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.conciso.entity.History;
import de.conciso.service.IHistoryService;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HistoryController {

    private static final Logger logger = Logger.getLogger(HistoryController.class);

    public HistoryController() {

    }

    @Autowired
    private IHistoryService historyService;

    @ResponseBody
    @RequestMapping(value = "/addNewHistoryItem")
    public void addNewHistoryItem(@RequestBody History history) {

        int historySizeExceeds = historyService.getAllHistories().size() - History.MAX_HISTORY_QUEUE;
        logger.info("Adding the next History. Data: ");
        history.setCreationTime(System.currentTimeMillis());
        historyService.createHistory(history);

        for (int i = 0; i < historySizeExceeds; i++)
            historyService.deleteEarliestHistory();

    }


    @RequestMapping(value = "/getHistory", method = RequestMethod.GET)
    public @ResponseBody
    String getHistory() {

        logger.info("Getting the History.");
        List<History> historyList = historyService.getAllHistories();
        Gson gsonBuilder = new GsonBuilder().create();
        String jsonFromPojo = gsonBuilder.toJson(historyList);
        return jsonFromPojo;
    }

    @RequestMapping(value = "/")
    public ModelAndView getIndex() {

        logger.info("Getting index");

        return new ModelAndView("index");
    }


}