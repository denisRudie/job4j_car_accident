package ru.job4j.accident.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccidentJdbcTemplate implements Store {

    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Accident> findAllAccidents() {
        List<Accident> accidents = jdbc.query(
                "select * from accident",
                new BeanPropertyRowMapper<>(Accident.class)
        );

        for (Accident accident : accidents) {
            int id = accident.getId();

            AccidentType type = jdbc.query("select at.* " +
                            "from accident a " +
                            "left join accident_type at on a.type_id = at.id " +
                            "where a.id = ?",
                    new BeanPropertyRowMapper<>(AccidentType.class), id).get(0);
            accident.setType(type);

            List<Rule> rules = jdbc.query("select ar.* " +
                            "from accident a " +
                            "inner join accident_accident_rule aar on a.id = aar.accident_id " +
                            "left join accident_rule ar on aar.rules_id = ar.id " +
                            "where a.id = ?",
                    new BeanPropertyRowMapper<>(Rule.class), id);
            accident.setRules(rules);
        }

        return accidents;
    }

    @Override
    public List<AccidentType> findAllAccidentTypes() {
        return jdbc.query("select * from accident_type ",
                new BeanPropertyRowMapper<>(AccidentType.class));
    }

    @Override
    public List<Rule> findAllRules() {
        return jdbc.query("select * from accident_rule ",
                new BeanPropertyRowMapper<>(Rule.class));
    }

    @Override
    public void create(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "insert into accident (name, text, address, type_id) values (?,?,?,?)",
                            new String[] { "id" });
                    ps.setString(1, accident.getName());
                    ps.setString(2, accident.getText());
                    ps.setString(3, accident.getAddress());
                    ps.setInt(4, accident.getType().getId());
                    return ps;
                }, keyHolder);

        for (Rule rule : accident.getRules()) {
            jdbc.update("insert into accident_accident_rule (accident_id, rules_id) VALUES (?, ?)",
                    keyHolder.getKey().intValue(), rule.getId());
        }
    }

    @Override
    public void update(Accident accident) {
        jdbc.update("update accident a set name = ?, text = ?, address = ?, type_id = ?" +
                "where a.id = ?",
                accident.getName(), accident.getText(), accident.getAddress(),
                accident.getType().getId(), accident.getId());

        List<Rule> currentRules = jdbc.query("select ar.* " +
                        "from accident a " +
                        "inner join accident_accident_rule aar on a.id = aar.accident_id " +
                        "left join accident_rule ar on aar.rules_id = ar.id " +
                        "where a.id = ?",
                new BeanPropertyRowMapper<>(Rule.class), accident.getId());

        List<Rule> newRules = accident.getRules();

        List<Rule> diff = new ArrayList<>(currentRules);
        diff.removeAll(newRules);
        for (Rule currentRule : diff) {
            jdbc.update("delete from accident_accident_rule where rules_id = ? and accident_id = ?",
                    currentRule.getId(), accident.getId());
        }
        diff = new ArrayList<>(newRules);
        diff.removeAll(currentRules);
        for (Rule newRule : diff) {
            jdbc.update("insert into accident_accident_rule (accident_id, rules_id)" +
                    "values (?, ?)", accident.getId(), newRule.getId());
        }
    }

    @Override
    public Accident getAccidentById(int id) {
        Accident accident = jdbc.query("select * from accident where id = ?",
                new BeanPropertyRowMapper<>(Accident.class), id).get(0);

        AccidentType type = jdbc.query("select at.* " +
                        "from accident a " +
                        "left join accident_type at on a.type_id = at.id " +
                        "where a.id = ?",
                new BeanPropertyRowMapper<>(AccidentType.class), id).get(0);
        accident.setType(type);

        List<Rule> rules = jdbc.query("select ar.* " +
                        "from accident a " +
                        "inner join accident_accident_rule aar on a.id = aar.accident_id " +
                        "left join accident_rule ar on aar.rules_id = ar.id " +
                        "where a.id = ?",
                new BeanPropertyRowMapper<>(Rule.class), id);
        accident.setRules(rules);

        return accident;
    }

    @Override
    public AccidentType getAccidentTypeById(int id) {
        return jdbc.query("select * from accident_type where id = ?",
                new BeanPropertyRowMapper<>(AccidentType.class), id).get(0);
    }

    @Override
    public Rule getRuleById(int id) {
        return jdbc.query("select * from accident_rule where id = ?",
                new BeanPropertyRowMapper<>(Rule.class), id).get(0);
    }
}
