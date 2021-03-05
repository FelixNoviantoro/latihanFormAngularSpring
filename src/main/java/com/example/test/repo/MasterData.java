package com.example.test.repo;


import com.example.test.model.Pendaftaran;
import com.example.test.model.Music;
import com.example.test.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

@Repository
public class MasterData {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private Sql2o sql2o;

// ==============SELECT=================================================================

    public Map<String, List<Object>> fetchList (String cari) {
        Map<String, List<Object>> data = new HashMap<>();
        List<Object> arrPendaftaran = new ArrayList<>();
        List<Object> arrMusic = new ArrayList<>();
        if (ObjectUtils.isEmpty(cari)) {
            cari = "";
        }
        jdbcTemplate.query("SELECT * FROM pendaftaran INNER JOIN music ON pendaftaran.memberId = music.memberId WHERE memberName LIKE concat('%', ? , '%')",
                (rs,rw) -> {
    
                    Pendaftaran pendaftaran = new Pendaftaran();
                    pendaftaran.setMemberId(rs.getInt("memberId"));
                    pendaftaran.setMemberName(rs.getString("memberName"));
                    pendaftaran.setMemberParent(rs.getString("memberParent"));
                    pendaftaran.setMemberBirthday(rs.getDate("memberBirthday"));
                    pendaftaran.setMemberPhone(rs.getInt("memberPhone"));
                    pendaftaran.setMemberEmail(rs.getString("memberEmail"));
                    pendaftaran.setMemberSkill(rs.getBoolean("memberSkill"));
                    arrPendaftaran.add(pendaftaran);

                    Music music = new Music();
                    music.setMemberId(rs.getInt("memberId"));
                    music.setMusicGitarKlasik(rs.getBoolean("musicGitarKlasik"));
                    music.setMusicGitarPop(rs.getBoolean("musicGitarPop"));
                    music.setMusicGitarElektrik(rs.getBoolean("musicGitarElektrik"));
                    music.setMusicBassElektrik(rs.getBoolean("musicBassElektrik"));
                    music.setMusicPianoKlasik(rs.getBoolean("musicPianoKlasik"));
                    music.setMusicPianoPop(rs.getBoolean("musicPianoPop"));
                    music.setMusicKeyboard(rs.getBoolean("musicKeyboard"));
                    music.setMusicDrum(rs.getBoolean("musicDrum"));
                    music.setMusicBiola(rs.getBoolean("musicBiola"));
                    music.setMusicTerapi(rs.getBoolean("musicTerapi"));
                    arrMusic.add(music);

                    data.put("pendaftaran", arrPendaftaran);
                    data.put("music", arrMusic);

                    return data;
                }, cari);
        return data;
    }
    

    public Map<String, Object> fetchListById (Integer id) {
        Map<String, Object> data = new HashMap<>();
        jdbcTemplate.queryForObject("SELECT * FROM pendaftaran INNER JOIN music ON pendaftaran.memberId = music.memberId WHERE pendaftaran.memberId = ?",
                (rs,rw) -> {

                    Pendaftaran pendaftaran = new Pendaftaran();
                    pendaftaran.setMemberId(rs.getInt("memberId"));
                    pendaftaran.setMemberName(rs.getString("memberName"));
                    pendaftaran.setMemberParent(rs.getString("memberParent"));
                    pendaftaran.setMemberBirthday(rs.getDate("memberBirthday"));
                    pendaftaran.setMemberPhone(rs.getInt("memberPhone"));
                    pendaftaran.setMemberEmail(rs.getString("memberEmail"));
                    pendaftaran.setMemberSkill(rs.getBoolean("memberSkill"));

                    Music music = new Music();
                    music.setMemberId(rs.getInt("memberId"));
                    music.setMusicGitarKlasik(rs.getBoolean("musicGitarKlasik"));
                    music.setMusicGitarPop(rs.getBoolean("musicGitarPop"));
                    music.setMusicGitarElektrik(rs.getBoolean("musicGitarElektrik"));
                    music.setMusicBassElektrik(rs.getBoolean("musicBassElektrik"));
                    music.setMusicPianoKlasik(rs.getBoolean("musicPianoKlasik"));
                    music.setMusicPianoPop(rs.getBoolean("musicPianoPop"));
                    music.setMusicKeyboard(rs.getBoolean("musicKeyboard"));
                    music.setMusicDrum(rs.getBoolean("musicDrum"));
                    music.setMusicBiola(rs.getBoolean("musicBiola"));
                    music.setMusicVocal(rs.getBoolean("musicVocal"));
                    music.setMusicTerapi(rs.getBoolean("musicTerapi"));

                    data.put("pendaftaran", pendaftaran);
                    data.put("music", music);

                    return data;
                }, id);
        return data;
    }

// =======================INSERT==========================================================

    public void insertPendaftaran (Pendaftaran pendaftaran) {
        final String query = "INSERT INTO pendaftaran values (?,?,?,?,?,?,?)";
        jdbcTemplate.update(query,
                pendaftaran.getMemberId(),
                pendaftaran.getMemberName(),
                pendaftaran.getMemberBirthday(),
                pendaftaran.getMemberParent(),
                pendaftaran.getMemberPhone(),
                pendaftaran.getMemberEmail(),
                pendaftaran.isMemberSkill());
    }
    public void insertMusic (Music music) {
        final String query = "INSERT INTO music values (?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(query,
                music.getMemberId(),
                music.isMusicGitarKlasik(),
                music.isMusicGitarPop(),
                music.isMusicGitarElektrik(),
                music.isMusicBassElektrik(),
                music.isMusicPianoKlasik(),
                music.isMusicPianoPop(),
                music.isMusicKeyboard(),
                music.isMusicDrum(),
                music.isMusicBiola(),
                music.isMusicVocal(),
                music.isMusicTerapi());
    }

// ======================UPDATE==============================================================

    public void updatePendaftaran (Pendaftaran pendaftaran) {
        final String query = "UPDATE pendaftaran SET memberPhone = ? , memberEmail = ? WHERE memberId = ?";
        jdbcTemplate.update(query,
                pendaftaran.getMemberPhone(),
                pendaftaran.getMemberEmail(),
                pendaftaran.getMemberId());
    }

    public void updateMusic (Music music) {
        final String query = "UPDATE music SET musicGitarKlasik = ?, musicGitarPop = ?, musicGitarElektrik = ?, musicBassElektrik = ?, musicPianoKlasik = ?, musicPianoPop = ?, musicKeyboard = ?,musicDrum = ?, musicBiola = ?, musicVocal = ?, musicTerapi = ?  WHERE memberId = ?";
        jdbcTemplate.update(query,
                music.isMusicGitarKlasik(),
                music.isMusicGitarPop(),
                music.isMusicGitarElektrik(),
                music.isMusicBassElektrik(),
                music.isMusicPianoKlasik(),
                music.isMusicPianoPop(),
                music.isMusicKeyboard(),
                music.isMusicDrum(),
                music.isMusicBiola(),
                music.isMusicVocal(),
                music.isMusicTerapi(),
                music.getMemberId());
    }

// ======================DELETE================================================================

    public void deletePendaftaran (Integer id) {
        final String query = "DELETE FROM pendaftaran WHERE memberId = ?";
        jdbcTemplate.update(query, id);
    }
    public void deleteMusic (Integer id) {
        final String query = "DELETE FROM music WHERE memberId = ?";
        jdbcTemplate.update(query, id);
    }


// ======================LOGIN================================================================

    public Login getUser (String name) {
        try (Connection con = sql2o.open()) {
            final String query = "SELECT * FROM admin WHERE adminName = :name";
            return con.createQuery(query)
                .addParameter("name", name)
                .executeAndFetchFirst(Login.class);
        }
    }

    public void updateJam (Login login) {
        final String query = "update admin set adminJam = now() where adminName = ?";
        jdbcTemplate.update(query, login.getAdminName());
    }

    public void updateIsLogin (Login login) {
        final String query = "update admin set adminIsLogin = ? where adminName = ?";
        jdbcTemplate.update(query, login.isAdminIsLogin(), login.getAdminName());
    }

}
