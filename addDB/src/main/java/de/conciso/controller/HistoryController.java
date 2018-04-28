package de.conciso.controller;

import de.conciso.entity.History;
import de.conciso.service.IHistoryService;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class HistoryController {

    private static final Logger logger = Logger.getLogger(HistoryController.class);

    public HistoryController() {

    }

    @Autowired
    private IHistoryService historyService;


    @RequestMapping("saveHistory")
    public ModelAndView saveHistory(@ModelAttribute History history) {
        logger.info("Saving the History. Data : " + history);
        historyService.createHistory(history);
        return new ModelAndView("redirect:getAllHistorys");
    }

    @RequestMapping("deleteHistory")
    public ModelAndView deleteHistory(@RequestParam long id) {
        logger.info("Deleting the History. Id : " + id);
        historyService.deleteHistory(id);
        return new ModelAndView("redirect:getAllHistories");
    }

    @RequestMapping(value = {"getAllHistories", "/"})
    public ModelAndView getAllHistories() {
        logger.info("Getting the all Histories.");
        List<History> historyList = historyService.getAllHistories();
        return new ModelAndView("historyList", "historyList", historyList);
    }


}