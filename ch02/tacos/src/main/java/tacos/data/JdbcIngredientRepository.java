package tacos.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tacos.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcIngredientRepository implements IngredRepo {
    private JdbcTemplate jdbc;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Iterable<Ingredient> findAll() {
        return jdbc.query("select id, name, type from Ingredient ",
                this::mapRowToIngredient
        );
    }

    @Override
    public Ingredient findById(String id) {
        Ingredient ingred = jdbc.queryForObject(
                "select id, name, type from Ingredient where id=?",
                this::mapRowToIngredient, id);
        return ingred;
    }


    private Ingredient mapRowToIngredient(ResultSet rs, int id) throws SQLException {
        return new Ingredient(
                rs.getString("id"),
                rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type"))
        );

    }


}
