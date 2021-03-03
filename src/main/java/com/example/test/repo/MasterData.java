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

@Repository
public class MasterData {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private Sql2o sql2o;

// ==============SELECT=================================================================

    public List<Pendaftaran> fetchListPendaftaran (String name) {
        try(Connection con = sql2o.open()) {
            if (ObjectUtils.isEmpty(name)) {
                name = "";
            }
            final String query = "SELECT * FROM pendaftaran INNER JOIN music ON pendaftaran.memberId = music.memberId WHERE memberName LIKE concat('%', :name , '%')";
            return con.createQuery(query)
                    .addParameter(":name", name)
                    .executeAndFetch(Pendaftaran.class);
        }
    }
    
    public Pendaftaran fetchMemberById (Integer id) {
        try(Connection con = sql2o.open()){
            final String query = "SELECT * FROM pendaftaran FULL OUTER JOIN ON pendaftaran.memberId = music.memberId WHERE memberId = :id";
            return con.createQuery(query)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Pendaftaran.class);
        }
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
        final String query = "INSERT INTO music values (?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(query,
                music.isMusicGitarKlasik(),
                music.isMusicGitarPop(),
                music.isMusicGitarElektrik(),
                music.isMusicBassElektrik(),
                music.isMusicPianoElektrik(),
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

// ======================DELETE================================================================

    public void deletePendaftaran (Integer id) {
        final String query = "DELETE FROM pendaftaran WHERE memberId = ?";
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
}
