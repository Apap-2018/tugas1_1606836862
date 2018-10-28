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

//	@Override
//	public PegawaiModel findPegawaiTermuda(long idInstansi) {
//		PegawaiModel pegawaiTermuda = new PegawaiModel();
//		int counter = 0;
//		for (PegawaiModel pegawai:pegawaiDB.findAll()) {
//			if (pegawai.getInstansi().getId() == idInstansi) {
//				if (counter == 0) {
//					pegawaiTermuda = pegawai;
//					counter += 1;
//				}
//				else {
//					if (pegawai.getTanggalLahir().compareTo(pegawaiTermuda.getTanggalLahir()) > 0) {
//						pegawaiTermuda = pegawai;
//					}
//				}
//			}
//		}
//		return pegawaiTermuda;
//	}
//
//	@Override
//	public PegawaiModel findPegawaiTertua(long idInstansi) {
//		PegawaiModel pegawaiTertua = new PegawaiModel();
//		int counter = 0;
//		for (PegawaiModel pegawai:pegawaiDB.findAll()) {
//			if (pegawai.getInstansi().getId() == idInstansi) {
//				if (counter == 0) {
//					pegawaiTertua = pegawai;
//					counter += 1;
//				}
//				else {
//					if (pegawai.getTanggalLahir().compareTo(pegawaiTertua.getTanggalLahir()) < 0) {
//						pegawaiTertua = pegawai;
//					}
//				}
//			}
//		}
//		return pegawaiTertua;
//	}

	@Override
	public List<PegawaiModel> getAllPegawai() {
		return pegawaiDB.findAll();
	}

}