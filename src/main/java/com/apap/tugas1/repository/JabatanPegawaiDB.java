package com.apap.tugas1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apap.tugas1.model.JabatanPegawaiModel;

public interface JabatanPegawaiDB extends JpaRepository<JabatanPegawaiModel, Long> {
	
}
