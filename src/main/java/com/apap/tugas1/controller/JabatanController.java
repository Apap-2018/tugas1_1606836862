package com.apap.tugas1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.service.JabatanService;


@Controller
public class JabatanController {
	
	@Autowired 
	private JabatanService jabatanService;

	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.GET)
	private String addJabatan(Model model) {
		JabatanModel jabatan = new JabatanModel();
		return "tambah-jabatan";
	}

	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.POST)
	private String addJabatanSubmit(@ModelAttribute JabatanModel jabatan, Model model) {
		jabatanService.addJabatan(jabatan);
		model.addAttribute("jabatan", jabatan);
		return "sukses-add-jabatan";
	}
	
	@RequestMapping(value = "/jabatan/view", method = RequestMethod.GET)
	public String viewJabatan(@RequestParam("idJabatan") long idJabatan, Model model) {
		JabatanModel jabatan = jabatanService.getJabatanDetailById(idJabatan);
		model.addAttribute("jabatan", jabatan);
		return "view-jabatan";
	}
	
	@RequestMapping(value = "/jabatan/ubah", method = RequestMethod.GET)
	public String ubahJabatan(@RequestParam("idJabatan") String idJabatan, Model model) {
		Long id = Long.parseLong(idJabatan);
		JabatanModel jabatan = jabatanService.getJabatanDetailById(id);
		model.addAttribute("jabatan", jabatan);
		return "ubah-jabatan";
	}
	
	@RequestMapping(value = "/jabatan/ubah", method = RequestMethod.POST)
	public String ubahJabatanSubmit(@ModelAttribute JabatanModel jabatan, Model model) {
		jabatanService.addJabatan(jabatan);
		model.addAttribute("jabatan", jabatan);
		return "sukses-ubah-jabatan";
	}
	
	@RequestMapping(value = "/jabatan/hapus", method = RequestMethod.POST)
	public String hapusJabatanSubmit(@ModelAttribute JabatanModel jabatan, Model model) {
		JabatanModel deleteJabatan = jabatanService.getJabatanDetailById(jabatan.getId());
		model.addAttribute("jabatan", deleteJabatan);
		if (deleteJabatan.jabatanSize() != 0) {
			return "gagal-delete-jabatan";
		}
		else {
			jabatanService.deleteJabatan(deleteJabatan);
			return "sukses-delete-jabatan";
		}
	}
	
	@RequestMapping(value = "/jabatan/viewall", method = RequestMethod.GET)
	public String viewAllJabatan(Model model) {
		List<JabatanModel> listJabatan = jabatanService.findAllJabatan();
		for (JabatanModel jabatan:listJabatan) {
			jabatan.setJumlahPegawai(jabatan.jabatanSize());
		}
		model.addAttribute("listJabatan", listJabatan);
		return "view-all-jabatan";
	}
}