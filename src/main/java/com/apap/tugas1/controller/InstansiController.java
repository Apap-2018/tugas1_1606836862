package com.apap.tugas1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.service.InstansiService;


@Controller
public class InstansiController {
	@Autowired
	private InstansiService instansiService;
	
	@RequestMapping(value = "/instansi-get", method = RequestMethod.GET)
	public @ResponseBody InstansiModel getInstansiById(@RequestParam(value = "instansiId", required = true) long instansiId) {
	    InstansiModel instansi = instansiService.getInstansiById(instansiId);
	    System.out.println(instansi.getNama());
	    return instansi;
	}
	
	@RequestMapping(value = "/instansi-get-ubah", method = RequestMethod.GET)
	public @ResponseBody InstansiModel getInstansiById(@RequestParam(value = "instansiId", required = true) String instansiId) {
		String id = instansiId.substring(0,4);
		InstansiModel instansi = instansiService.getInstansiById(Long.parseLong(id));
	    //System.out.println(instansi.getNama());
	    return instansi;
	}
}
