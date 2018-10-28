package com.apap.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.PegawaiDB;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService {
	@Autowired
	private PegawaiDB pegawaiDB;

	@Override
	public PegawaiModel getPegawaiDetailByNip(String nip) {
		return pegawaiDB.findByNip(nip);
	}

	@Override
	public double getGajiLengkapByNip(String nip) {
		double gajiLengkap = 0;
		PegawaiModel pegawai = this.getPegawaiDetailByNip(nip);
		double gajiTerbesar = 0; 
		for (JabatanModel jabatan:pegawai.getJabatanList()) {
			if (jabatan.getGajiPokok() > gajiTerbesar) {
				gajiTerbesar = jabatan.getGajiPokok();
			}
		}
		gajiLengkap += gajiTerbesar;
		double presentaseTunjangan = pegawai.getInstansi().getProvinsi().getPresentaseTunjangan();
		gajiLengkap += (gajiLengkap * presentaseTunjangan/100);
		return gajiLengkap;
	}

	@Override
	public void addPegawai(PegawaiModel pegawai) {
		pegawaiDB.save(pegawai);
	}


	@Override
	public List<PegawaiModel> getAllPegawai() {
		return pegawaiDB.findAll();
	}

}