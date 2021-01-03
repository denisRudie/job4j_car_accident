package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

//@Repository
public class AccidentHibernate implements Store {

    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    private <T> T tx(final Function<Session, T> command) {
        T rsl;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            rsl = command.apply(session);
            session.getTransaction().commit();
        }
        return rsl;
    }

    private void consume(final Consumer<Session> command) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            command.accept(session);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Accident> findAllAccidents() {
        return tx(session -> session.createQuery("select distinct a from Accident a " +
                "join fetch a.type " +
                "join fetch a.rules")
                .list()
        );
    }

    @Override
    public List<AccidentType> findAllAccidentTypes() {
        return tx(session -> session
                .createQuery("from AccidentType ")
                .list());
    }

    @Override
    public List<Rule> findAllRules() {
        return tx(session -> session
                .createQuery("from Rule ")
                .list());
    }

    @Override
    public void create(Accident accident) {
        consume(session -> session.save(accident));
    }

    @Override
    public void update(Accident accident) {
        consume(session -> session.update(accident));
    }

    @Override
    public Accident getAccidentById(int id) {
        return tx(session -> (Accident) session
                .createQuery("select distinct a from Accident a " +
                        "left join fetch a.rules " +
                        "where a.id=:x ")
                .setParameter("x", id)
                .uniqueResult());
    }

    @Override
    public AccidentType getAccidentTypeById(int id) {
        return tx(session -> session.get(AccidentType.class, id));
    }

    @Override
    public Rule getRuleById(int id) {
        return tx(session -> session.get(Rule.class, id));
    }
}
