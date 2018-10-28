package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.JabatanPegawaiModel;

public interface JabatanPegawaiService {
	List<JabatanPegawaiModel> getJabatanByPegawaiId(long id);
	long sizeJabatanPegawai();
	JabatanPegawaiModel checkWho(long id);
	boolean isExistByJabatanId(long id);
}
