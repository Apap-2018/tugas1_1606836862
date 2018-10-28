package com.apap.tugas1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.JabatanService;
import com.apap.tugas1.service.PegawaiService;
import com.apap.tugas1.service.ProvinsiService;


@Controller
public class PegawaiController {
	@Autowired
	private PegawaiService pegawaiService;

	@Autowired
	private ProvinsiService provinsiService;
	
	@Autowired 
	private JabatanService jabatanService;
	
	@Autowired
	private InstansiService instansiService;

	@RequestMapping("/")
	private String index(Model model) {
		model.addAttribute("listJabatan",jabatanService.findAllJabatan());
		model.addAttribute("listInstansi",instansiService.findAllInstansi());
		return "index";
	}

	@RequestMapping(value = "/pegawai")
	public String viewPegawai(@RequestParam("nip") String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNip(nip);

		model.addAttribute("pegawai", pegawai);
		model.addAttribute("gajiLengkap", Math.round(pegawaiService.getGajiLengkapByNip(nip)));
		model.addAttribute("jabatanList", pegawai.getJabatanList());
		
		return "view-pegawai";
		
	}	

	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.GET)
	private String addPegawai(Model model) {
		PegawaiModel pegawai = new PegawaiModel();
		pegawai.setInstansi(new InstansiModel());
		
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("listProvinsi", provinsiService.getProvinsiList());
		model.addAttribute("listJabatan", jabatanService.findAllJabatan());
		
		return "tambah-pegawai";
	}

	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
	private String addPegwawaiSubmit(@ModelAttribute PegawaiModel pegawai, Model model) {
		String nip = "";
		
		nip += pegawai.getInstansi().getId();
		
		String[] tglLahir = pegawai.getTanggalLahir().toString().split("-");
		String tglLahirString = tglLahir[2] + tglLahir[1] + tglLahir[0].substring(2, 4);
		nip += tglLahirString;

		nip += pegawai.getTahunMasuk();

		int counterSama = 1;
		for (PegawaiModel pegawaiInstansi:pegawai.getInstansi().getPegawaiInstansi()) {
			if (pegawaiInstansi.getTahunMasuk().equals(pegawai.getTahunMasuk()) && pegawaiInstansi.getTanggalLahir().equals(pegawai.getTanggalLahir())) {
				counterSama += 1;
			}	
		}
		nip += "0" + counterSama;
		
		pegawai.setNip(nip);
		pegawaiService.addPegawai(pegawai);
		model.addAttribute("pegawai", pegawai);
		return "sukses-add-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/ubah")
	public String changePegawai(@RequestParam("nip") String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNip(nip);
		//System.out.println(pegawai.getId());
		
		model.addAttribute("listProvinsi", provinsiService.getProvinsiList());
		model.addAttribute("listJabatan", jabatanService.findAllJabatan());
		model.addAttribute("pegawai", pegawai);
		return "change-pegawai";	
	}	
	
	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.POST)
	private String ubahPegawaiSubmit(@ModelAttribute PegawaiModel pegawai, Model model) {
		String nip = "";
		
		nip += pegawai.getInstansi().getId();
		//System.out.println(pegawai.getInstansi().getId());
		//System.out.println(pegawai.getId());
		
		String[] tglLahir = pegawai.getTanggalLahir().toString().split("-");
		String tglLahirString = tglLahir[2] + tglLahir[1] + tglLahir[0].substring(2, 4);
		nip += tglLahirString;
		//System.out.println(pegawai.getTanggalLahir());
		
		nip += pegawai.getTahunMasuk();
		//System.out.println(pegawai.getTahunMasuk());
		
		int counterSama = 1;
		for (PegawaiModel pegawaiInstansi:pegawai.getInstansi().getPegawaiInstansi()) {
			if (pegawaiInstansi.getTahunMasuk().equals(pegawai.getTahunMasuk()) && pegawaiInstansi.getTanggalLahir().equals(pegawai.getTanggalLahir()) && pegawaiInstansi.getId() != pegawai.getId()) {
				counterSama += 1;
			}	
		}
		nip += "0" + counterSama;

		pegawai.setNip(nip);
		//System.out.println(pegawai.getNip());
		//System.out.println(pegawai.getId());
		pegawaiService.addPegawai(pegawai);
		model.addAttribute("pegawai", pegawai);
		return "sukses-change-pegawai";
	}
//	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.POST)
//	private String ubahProfilPegawaiSubmit(@ModelAttribute PegawaiModel pegawai, Model model) {
//		String nip = ""+ pegawai.getInstansi().getId();
//		
//		String[] arrTglLahir= pegawai.getTanggalLahir().toString().split("-");
//		String strTglLahir = arrTglLahir[2] + arrTglLahir[1] + arrTglLahir[0].substring(2, 4);
//		nip += strTglLahir;
//		
//		nip += pegawai.getTahunMasuk();
//
//		int counter = 1;
//		for (PegawaiModel pegawaiInstansi:pegawai.getInstansi().getPegawaiInstansi()) {
//			if (pegawaiInstansi.getTahunMasuk().equals(pegawai.getTahunMasuk()) && pegawaiInstansi.getTanggalLahir().equals(pegawai.getTanggalLahir()) && pegawaiInstansi.getId() != pegawai.getId()) {
//				counter++;
//			}	
//		}
//		nip += "0" + counter;
//		
//		pegawai.setNip(nip);
//		pegawaiService.addPegawai(pegawai);
//		model.addAttribute("pegawai", pegawai);
//		return "sukses-change-pegawai";
//	}
	
	@RequestMapping(value = "/pegawai/termuda-tertua")
	public String viewPegawaiUmur(@RequestParam("idInstansi") long idInstansi, Model model) {
		InstansiModel instansi = instansiService.getInstansiById(idInstansi);
//		PegawaiModel pegawaiTermuda = pegawaiService.findPegawaiTermuda(idInstansi);
//		PegawaiModel pegawaiTertua = pegawaiService.findPegawaiTertua(idInstansi);
		PegawaiModel pegawaiTermuda = instansi.getPegawaiByTermuda();
		PegawaiModel pegawaiTertua = instansi.getPegawaiByTertua();
		int gajiLengkapTermuda = (int) pegawaiService.getGajiLengkapByNip(pegawaiTermuda.getNip());
		int gajiLengkapTertua = (int) pegawaiService.getGajiLengkapByNip(pegawaiTertua.getNip());
		
		model.addAttribute("pegawaiTermuda", pegawaiTermuda);
		model.addAttribute("gajiLengkapTermuda", gajiLengkapTermuda);
		model.addAttribute("jabatanListTermuda", pegawaiTermuda.getJabatanList());
		
		model.addAttribute("pegawaiTertua", pegawaiTertua);
		model.addAttribute("gajiLengkapTertua", gajiLengkapTertua);
		model.addAttribute("jabatanListTertua", pegawaiTertua.getJabatanList());
		
		return "view-instansi";
	}		
	
	@RequestMapping(value = "/pegawai/cari")
	public String viewPegawaiFilter(Model model) {
		List<PegawaiModel> listPegawai = pegawaiService.getAllPegawai();
		model.addAttribute("listPegawai", listPegawai);
		return "view-pegawai-filter";
	}
	
}
