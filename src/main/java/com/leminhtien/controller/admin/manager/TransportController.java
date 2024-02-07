package com.leminhtien.controller.admin.manager;

import com.leminhtien.dto.TransportDTO;
import com.leminhtien.service.ITransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@PreAuthorize("hasAnyRole('MANAGER')")
@Controller(value = "managerTransport")
public class TransportController {
    @Autowired
    private ITransportService transportService;
    @RequestMapping(value = "/manager/transport",method = RequestMethod.GET)
    public ModelAndView findAll(@RequestParam(value = "page",defaultValue = "1") int page) {
        ModelAndView mav = new ModelAndView("/admin/transport/transport");
        int limit = 10;
        Pageable pageable = new PageRequest(page-1, limit);
        Page<TransportDTO> list = transportService.findAll(pageable);
            mav.addObject("model",list.getContent());
            mav.addObject("page",page);
            mav.addObject("limit", limit);
            mav.addObject("totalItem", list.getTotalElements());
        mav.addObject("totalPage", list.getTotalPages());
        return mav;
    }
}
