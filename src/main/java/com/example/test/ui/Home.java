package com.example.test.ui;

import com.example.test.model.Pendaftaran;
import com.example.test.model.Music;
import com.example.test.model.Login;
import com.example.test.repo.MasterData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

@Controller
public class Home {

    @Autowired
    private MasterData masterData;

    @GetMapping("api/memberlist")
    public ResponseEntity<List<Pendaftaran>> getMemberList(
            @RequestParam(name = "cari") String cari
    ) {
        return ResponseEntity.ok(masterData.fetchListPendaftaran(cari));
    }

    @GetMapping("api/memberById/{id}")
    public ResponseEntity<Pendaftaran> getMemberById(
            @PathVariable(name = "id") Integer id
    ) {
        return ResponseEntity.ok(masterData.fetchMemberById(id));
    }

    @PostMapping("api/insert")
    public ResponseEntity<Pendaftaran> insertMember(
            @RequestBody Pendaftaran pendaftaran
    ) {
        masterData.insertPendaftaran(pendaftaran);
        // masterData.insertMusic(music);/
        return ResponseEntity.ok(pendaftaran);
    }

    @PostMapping("api/update")
    public ResponseEntity<Pendaftaran> updateMember(
            @RequestBody Pendaftaran pendaftaran
    ) {
        masterData.updatePendaftaran(pendaftaran);
        return ResponseEntity.ok(pendaftaran);
    }

    @DeleteMapping("api/delete/{id}")
    public ResponseEntity<List<Pendaftaran>> deleteMember(
            @PathVariable(name = "id") Integer id
    ) {
        masterData.deletePendaftaran(id);
        return ResponseEntity.ok(masterData.fetchListPendaftaran(""));
    }

// =========================================================================================
    @PostMapping("api/login/{adminName}")
    public ResponseEntity<Login> loginForm (
        @PathVariable("adminName") String adminName,
        @RequestBody Login login,
        BindingResult bindingResult
    ){
        Login admin = masterData.getUser(adminName);
        if(login != null && Objects.equals(login.getAdminName(), admin.getAdminName()) && Objects.equals(login.getAdminPassword(), admin.getAdminPassword())) {
            admin.setAdminIsLogin(true);
        } else {
            admin.setAdminIsLogin(false);
        }
        return ResponseEntity.ok(admin);
    }

}
