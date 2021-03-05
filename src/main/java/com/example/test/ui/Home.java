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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

class Data {
    public Pendaftaran pendaftaran;
    public Music music;
}

@Controller
public class Home {

    @Autowired
    private MasterData masterData;
    private Object List;

    @GetMapping("api/memberlist")
    public ResponseEntity<Map<String, List<Object>>> getMemberList(
            @RequestParam(required = false, name = "cari") String cari
    ) {
        System.out.println(masterData.fetchList(cari));
        return ResponseEntity.ok(masterData.fetchList(cari));
    }

    @GetMapping("api/memberById/{id}")
    public ResponseEntity<Map<String, Object>> getMemberById(
            @PathVariable(name = "id") Integer id
    ) {
        System.out.println(masterData.fetchListById(id));
        return ResponseEntity.ok(masterData.fetchListById(id));
    }

    @PostMapping("api/insert")
    public ResponseEntity<String> insertMember(
            @RequestBody Data data
    ) {
        System.out.println(data.pendaftaran + " ======= " + data.music);
        masterData.insertPendaftaran(data.pendaftaran);
        masterData.insertMusic(data.music);
        return ResponseEntity.ok("Insert Berhasil");
    }

    @PostMapping("api/update")
    public ResponseEntity<String> updateMember(
            @RequestBody Data data
    ) {
        masterData.updatePendaftaran(data.pendaftaran);
        masterData.updateMusic(data.music);
        return ResponseEntity.ok("update berhasil");
    }

   @GetMapping("api/delete/{id}")
   public ResponseEntity<Map<String, List<Object>>> deleteMember(
           @PathVariable(name = "id") Integer id
   ) {
       masterData.deleteMusic(id);
       masterData.deletePendaftaran(id);
       return ResponseEntity.ok(masterData.fetchList(""));
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
            masterData.updateJam(admin);
            masterData.updateIsLogin(admin);
        } else {
            admin.setAdminIsLogin(false);
        }
        return ResponseEntity.ok(admin);
    }

    @PostMapping("api/logout/{adminName}")
    public ResponseEntity<Login> logoutForm (
        @PathVariable("adminName") String adminName,
        @RequestBody Login login,
        BindingResult bindingResult
    ) {
        Login admin = masterData.getUser(adminName);
        admin.setAdminIsLogin(false);
        masterData.updateIsLogin(admin);
        masterData.updateJam(admin);
        return ResponseEntity.ok(admin);
    }

}
